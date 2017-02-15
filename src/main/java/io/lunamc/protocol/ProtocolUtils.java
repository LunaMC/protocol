/*
 * Copyright (C) 2017 LunaMC (https://lunamc.io/)
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.lunamc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Utilities for read and write Minecraft-specific data types.
 */
public class ProtocolUtils {

    private static final short MIN_UNSIGNED_BYTE_VALUE = 0b00000000;
    private static final short MAX_UNSIGNED_BYTE_VALUE = 0b11111111;

    private ProtocolUtils() {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " is a utility class and should not be constructed");
    }

    /**
     * Write unsigned bytes represented by a short array.
     *
     * @param output The {@link ByteBuf} to which the values will be written
     * @param values The values which should be written
     * @throws NullPointerException Will be thrown if {@code output} or {@code values} is {@code null}
     * @throws IllegalArgumentException Will be thrown if a value of the given array is smaller than {@code 0b00000000}
     *                                  or greater than {@code 0b11111111}.
     */
    public static void writeUnsignedByteArray(ByteBuf output, short[] values) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(values, "values must not be null");

        writeVarInt(output, values.length);
        for (short value : values) {
            if (value < MIN_UNSIGNED_BYTE_VALUE || value > MAX_UNSIGNED_BYTE_VALUE)
                throw new IllegalArgumentException("Unsigned byte values must be between " + MIN_UNSIGNED_BYTE_VALUE + " and " + MAX_UNSIGNED_BYTE_VALUE);
            output.writeByte(value);
        }
    }

    public static short[] readUnsignedByteArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        short[] values = new short[readVarInt(input)];
        for (int i = 0; i < values.length; i++)
            values[i] = input.readUnsignedByte();
        return values;
    }

    public static void writeVarInt(ByteBuf output, int value) {
        Objects.requireNonNull(output, "output must not be null");

        byte temp;
        do {
            temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0)
                temp |= 0b10000000;
            output.writeByte(temp);
        } while (value != 0);
    }

    public static int readVarInt(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        int bytes = 0;
        int result = 0;
        byte b;
        do {
            if (!input.isReadable())
                throw new MalformedDataException("Malformed VarInt");
            b = input.readByte();
            result |= (b & 0b01111111) << (7 * bytes++);
            if (bytes > 5)
                throw new MalformedDataException("VarInt is too big");
        } while ((b & 0b10000000) != 0);
        return result;
    }

    public static void writeVarIntArray(ByteBuf output, int[] values) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(values, "values must not be null");

        writeVarInt(output, values.length);
        for (int value : values)
            writeVarInt(output, value);
    }

    public static int[] readVarIntArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        int[] values = new int[readVarInt(input)];
        for (int i = 0; i < values.length; i++)
            values[i] = readVarInt(input);
        return values;
    }

    public static void writeVarLong(ByteBuf output, long value) {
        Objects.requireNonNull(output, "output must not be null");

        byte temp;
        do {
            temp = (byte) (value & 0b01111111L);
            value >>>= 7;
            if (value != 0)
                temp |= 0b10000000;
            output.writeByte(temp);
        } while (value != 0);
    }

    public static long readVarLong(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        int bytes = 0;
        long result = 0;
        byte b;
        do {
            if (!input.isReadable())
                throw new MalformedDataException("Malformed VarLong");
            b = input.readByte();
            result |= (b & 0b01111111L) << (7 * bytes++);
            if (bytes > 10)
                throw new MalformedDataException("VarLong is too big");
        } while ((b & 0b10000000) != 0);
        return result;
    }

    public static void writeString(ByteBuf output, String value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        byte[] data = value.getBytes(CharsetUtil.UTF_8);
        writeVarInt(output, data.length);
        output.writeBytes(data);
    }

    public static String readString(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        byte[] data = new byte[readVarInt(input)];
        input.readBytes(data);
        return new String(data, CharsetUtil.UTF_8);
    }

    public static void writeStringArray(ByteBuf output, String[] value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        writeArray(output, value, ProtocolUtils::writeString);
    }

    public static String[] readStringArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        return readArray(input, String[]::new, ProtocolUtils::readString);
    }

    public static void writeUuid(ByteBuf output, UUID value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        output.writeLong(value.getMostSignificantBits());
        output.writeLong(value.getLeastSignificantBits());
    }

    public static UUID readUuid(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        return new UUID(input.readLong(), input.readLong());
    }

    public static <T> void writeArray(ByteBuf output, T[] elements, BiConsumer<ByteBuf, T> writer) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(elements, "elements must not be null");
        Objects.requireNonNull(writer, "writer must not be null");

        writeVarInt(output, elements.length);
        for (T element : elements)
            writer.accept(output, element);
    }

    public static <T> T[] readArray(ByteBuf input, Function<Integer, T[]> arrayConstructor, Function<ByteBuf, T> reader) {
        Objects.requireNonNull(input, "input must not be null");
        Objects.requireNonNull(arrayConstructor, "arrayConstructor must not be null");
        Objects.requireNonNull(reader, "reader must not be null");

        int length = readVarInt(input);
        T[] data = arrayConstructor.apply(length);
        for (int i = 0; i < data.length; i++)
            data[i] = reader.apply(input);
        return data;
    }
}
