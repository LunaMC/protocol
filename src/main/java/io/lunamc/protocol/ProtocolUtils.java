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
     * Write unsigned bytes represented by a short array. An array is written by writing the length of it as a
     * {@code VarInt} followed by the elements.
     *
     * @see #writeVarInt(ByteBuf, int)
     * @see ByteBuf#writeByte(int)
     * @param output The {@link ByteBuf} to which the values will be written
     * @param values The values which should be written
     * @throws NullPointerException Will be thrown if {@code output} or {@code values} is {@code null}
     * @throws IllegalArgumentException Will be thrown if a value of the given array is less than {@code 0b00000000}
     *                                  or greater than {@code 0b11111111}.
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
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

    /**
     * Reads an unsigned bytes array into a short array. An array is read by reading its length as a {@code VarInt}
     * followed by the elements.
     *
     * @see #readVarInt(ByteBuf)
     * @see ByteBuf#readUnsignedByte()
     * @param input The {@link ByteBuf} from which the values will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if the data stream ends before the array was read successfully
     * @throws MalformedDataException Will be thrown if the length-indicator is malformed
     * @return The read array
     */
    public static short[] readUnsignedByteArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        short[] values = new short[readVarInt(input)];
        for (int i = 0; i < values.length; i++)
            values[i] = input.readUnsignedByte();
        return values;
    }

    /**
     * Writes a {@code VarInt} to the specified {@link ByteBuf}. A {@code VarInt} uses the most significant bit of a
     * byte to indicate if this is the last data byte of the {@code VarInt} and the remaining 7 bits for the data. A
     * {@code VarInt} can be up to 5 bytes long.
     *
     * @param output The {@link ByteBuf} to which the {@code VarInt} will be written
     * @throws NullPointerException Will be thrown if {@code output} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     * @param value The value which should be written
     */
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

    /**
     * Reads a {@code VarInt} from the specified {@link ByteBuf}. A {@code VarInt} uses the most significant bit of a
     * byte to indicate if this is the last data byte of the {@code VarInt} and the remaining 7 bits for the data. A
     * {@code VarInt} can be up to 5 bytes long.
     *
     * @param input The {@link ByteBuf} from which the {@code VarInt} will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if the {@link ByteBuf} ends before the {@code VarInt} was read
     *                                or if the {@code VarInt} is not finished after 5 bytes
     * @return The read {@code VarInt}
     */
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

    /**
     * Writes an array of {@code VarInt}s to the specified {@link ByteBuf}. An array is written by writing the length
     * of it as a {@code VarInt} followed by the elements.
     *
     * @see #writeVarInt(ByteBuf, int)
     * @param output The {@link ByteBuf} to which the {@code VarInt} array will be written
     * @param values The values which should be written
     * @throws NullPointerException Will be thrown if {@code output} or {@code values} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     */
    public static void writeVarIntArray(ByteBuf output, int[] values) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(values, "values must not be null");

        writeVarInt(output, values.length);
        for (int value : values)
            writeVarInt(output, value);
    }

    /**
     * Reads an array of {@code VarInt}s from the specified {@link ByteBuf}. An array is read by reading its length as
     * a {@code VarInt} followed by the elements.
     *
     * @see #readVarInt(ByteBuf)
     * @param input The {@link ByteBuf} from which the {@code VarInt} array will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if an invalid {@code VarInt} was read
     * @return The read {@code VarInt} array
     */
    public static int[] readVarIntArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        int[] values = new int[readVarInt(input)];
        for (int i = 0; i < values.length; i++)
            values[i] = readVarInt(input);
        return values;
    }

    /**
     * Writes an array of {@code byte}s to the specified {@link ByteBuf}. An array is written by writing the length
     * of it as a {@code VarInt} followed by the elements.
     *
     * @see #writeVarInt(ByteBuf, int)
     * @see ByteBuf#writeByte(int)
     * @param output The {@link ByteBuf} to which the {@code VarInt} array will be written
     * @param values The values which should be written
     * @throws NullPointerException Will be thrown if {@code output} or {@code values} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     */
    public static void writeByteArray(ByteBuf output, byte[] values) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(values, "values must not be null");

        writeVarInt(output, values.length);
        for (byte value : values)
            output.writeByte(value);
    }

    /**
     * Reads an array of {@code byte}s from the specified {@link ByteBuf}. An array is read by reading its length as
     * a {@code VarInt} followed by the elements.
     *
     * @see #readVarInt(ByteBuf)
     * @see ByteBuf#readByte()
     * @param input The {@link ByteBuf} from which the {@code VarInt} array will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if an invalid {@code VarInt} was read
     * @return The read {@code VarInt} array
     */
    public static byte[] readByteArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        byte[] values = new byte[readVarInt(input)];
        for (int i = 0; i < values.length; i++)
            values[i] = input.readByte();
        return values;
    }

    /**
     * Writes a {@code VarLong} to the specified {@link ByteBuf}. A {@code VarLong} uses the most significant bit of a
     * byte to indicate if this is the last data byte of the {@code VarLong} and the remaining 7 bits for the data. A
     * {@code VarLong} can be up to 9 bytes long.
     *
     * @throws NullPointerException Will be thrown if {@code output} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     * @param output The {@link ByteBuf} to which the {@code VarLong} will be written
     * @param value The value which should be written
     */
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

    /**
     * Reads a {@code VarLong} from the specified {@link ByteBuf}. A {@code VarLong} uses the most significant bit of a
     * byte to indicate if this is the last data byte of the {@code VarLong} and the remaining 7 bits for the data. A
     * {@code VarLong} can be up to 5 bytes long.
     *
     * @param input The {@link ByteBuf} from which the {@code VarLong} will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if the {@link ByteBuf} ends before the {@code VarLong} was read
     *                                or if the {@code VarLong} is not finished after 5 bytes
     * @return The read {@code VarLong}
     */
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

    /**
     * Writes a {@link String} to the specified {@link ByteBuf}. A string is written by converting it to its utf8
     * bytes and writes the length of the resulting byte array as a {@code VarInt} followed by the data.
     *
     * @see #writeVarInt(ByteBuf, int)
     * @see ByteBuf#writeBytes(byte[])
     * @throws NullPointerException Will be thrown if {@code output} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     * @param output The {@link ByteBuf} to which the {@code VarLong} will be written
     * @param value The value which should be written
     */
    public static void writeString(ByteBuf output, String value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        byte[] data = value.getBytes(CharsetUtil.UTF_8);
        writeVarInt(output, data.length);
        output.writeBytes(data);
    }

    /**
     * Reads a {@link String} from the specified {@link ByteBuf}. A string is read by reading the length of the string
     * data as a {@code VarInt} followed by the utf8-data as a byte array.
     *
     * @see #readVarInt(ByteBuf)
     * @see ByteBuf#readBytes(byte[])
     * @param input The {@link ByteBuf} from which the {@link String} will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if an invalid {@code VarInt} was read
     * @throws IndexOutOfBoundsException Will be thrown if the data stream ends before the array was read successfully
     * @return The read {@link String}
     */
    public static String readString(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        byte[] data = new byte[readVarInt(input)];
        input.readBytes(data);
        return new String(data, CharsetUtil.UTF_8);
    }

    /**
     * Writes a string array to the specified {@link ByteBuf}. An array is written by writing the length of it
     * as a {@code VarInt} followed by the elements.
     *
     * @see #writeString(ByteBuf, String)
     * @see #writeArray(ByteBuf, Object[], BiConsumer)
     * @throws NullPointerException Will be thrown if {@code output} or {@code value} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     * @param output The {@link ByteBuf} to which the string array will be written
     * @param value The value which should be written
     */
    public static void writeStringArray(ByteBuf output, String[] value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        writeArray(output, value, ProtocolUtils::writeString);
    }

    /**
     * Reads a string array from the specified {@link ByteBuf}. An array is read by reading its length as a
     * {@code VarInt} followed by the elements.
     *
     * @see #readString(ByteBuf)
     * @see #readArray(ByteBuf, Function, Function)
     * @param input input The {@link ByteBuf} from which the {@link String} will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws MalformedDataException Will be thrown if an invalid {@code VarInt} was read
     * @throws IndexOutOfBoundsException Will be thrown if the data stream ends before the array was read successfully
     * @return The read string array
     */
    public static String[] readStringArray(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        return readArray(input, String[]::new, ProtocolUtils::readString);
    }

    /**
     * Writes an {@link UUID} to the specified {@link ByteBuf}. An uuid is written by writes the 8 most significant
     * bits followed by the 8 least significant bits.
     *
     * @see UUID#getMostSignificantBits()
     * @see UUID#getLeastSignificantBits()
     * @see ByteBuf#writeLong(long)
     * @param output The {@link ByteBuf} to which the {@link UUID} will be written
     * @param value The value which should be written
     * @throws NullPointerException Will be thrown if {@code output} or {@code value} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     */
    public static void writeUuid(ByteBuf output, UUID value) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(value, "value must not be null");

        output.writeLong(value.getMostSignificantBits());
        output.writeLong(value.getLeastSignificantBits());
    }

    /**
     * Reads an {@link UUID} from the specified {@link ByteBuf}. An uuid is read by reads the 8 most significant bits
     * an the 8 least significant bits as long values.
     *
     * @see UUID#UUID(long, long)
     * @see ByteBuf#readLong()
     * @param input input The {@link ByteBuf} from which the {@link UUID} will be read
     * @throws NullPointerException Will be thrown if {@code input} is {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if the data stream ends before the {@link UUID} was read
     *                                   successful
     * @return The read {@link UUID}
     */
    public static UUID readUuid(ByteBuf input) {
        Objects.requireNonNull(input, "input must not be null");

        return new UUID(input.readLong(), input.readLong());
    }

    /**
     * Writes any value as an array to the specified {@link ByteBuf} by using {@code writer}. An array is written by
     * writing the length of it as a {@code VarInt} followed by the elements.
     *
     * @see #readVarInt(ByteBuf)
     * @param output The {@link ByteBuf} to which the string array will be written
     * @param elements The values which should be written
     * @param writer The {@link Function} which will write one element to the specified {@link ByteBuf}
     * @param <T> The type of the elements
     * @throws NullPointerException Will be thrown if {@code output}, {@code elements} or {@code writer} is
     *                              {@code null}
     * @throws IndexOutOfBoundsException Will be thrown if {@code output} is not able to handle a write call
     */
    public static <T> void writeArray(ByteBuf output, T[] elements, BiConsumer<ByteBuf, T> writer) {
        Objects.requireNonNull(output, "output must not be null");
        Objects.requireNonNull(elements, "elements must not be null");
        Objects.requireNonNull(writer, "writer must not be null");

        writeVarInt(output, elements.length);
        for (T element : elements)
            writer.accept(output, element);
    }

    /**
     * Reads any array from the specified {@link ByteBuf} by using {@code reader}. The target array will be returned
     * by the {@code arrayConstructor} function. An array is read by reading its length as a {@code VarInt} followed
     * by the elements.
     *
     * @see #writeVarInt(ByteBuf, int)
     * @param input The {@link ByteBuf} from which the array will be read
     * @param arrayConstructor A {@link Function} which must return an array
     * @param reader A {@link Function} which will read one element from the specified {@link ByteBuf}
     * @param <T> The type of the elements
     * @throws NullPointerException Will be thrown if {@code input} {@code arrayConstructor} or {@code reader} is
     *                              {@code null}
     * @throws MalformedDataException Will be thrown if an invalid {@code VarInt} was read
     * @throws IndexOutOfBoundsException Will be thrown if the data stream ends before the array was read successfully
     * @throws IllegalStateException Will be thrown if the constructed array is too small
     * @return The read array
     */
    public static <T> T[] readArray(ByteBuf input, Function<Integer, T[]> arrayConstructor, Function<ByteBuf, T> reader) {
        Objects.requireNonNull(input, "input must not be null");
        Objects.requireNonNull(arrayConstructor, "arrayConstructor must not be null");
        Objects.requireNonNull(reader, "reader must not be null");

        int length = readVarInt(input);
        T[] data = arrayConstructor.apply(length);
        if (data.length < length)
            throw new IllegalStateException("Constructed array is too small (size: " + data.length + ", required: " + length + ")");
        for (int i = 0; i < data.length; i++)
            data[i] = reader.apply(input);
        return data;
    }
}
