/*
 *  Copyright 2017 LunaMC.io
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.lunamc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ProtocolUtilsTest {

    @Test
    public void testWriteReadUnsignedByteArray() {
        short[] test = new short[] { 0x00, 0x01, 0xff };
        ByteBuf buffer = Unpooled.buffer();
        ProtocolUtils.writeUnsignedByteArray(buffer, test);
        Assert.assertArrayEquals(test, ProtocolUtils.readUnsignedByteArray(buffer));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteNegativeShortValueAsAnUnsignedByte() {
        ProtocolUtils.writeUnsignedByteArray(Unpooled.buffer(), new short[] { 0b00000000 - 1 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWriteExceededShortValueAsAnUnsignedByte() {
        ProtocolUtils.writeUnsignedByteArray(Unpooled.buffer(), new short[] { 0b11111111 + 1 });
    }

    @Test
    public void testWriteVarInt() {
        // Test against raw reference VarInts of wiki.vg to ensure compatibility with Minecraft
        Assert.assertArrayEquals(new byte[] { 0x00 }, createVarInt(0));
        Assert.assertArrayEquals(new byte[] { 0x01 }, createVarInt(1));
        Assert.assertArrayEquals(new byte[] { 0x02 }, createVarInt(2));
        Assert.assertArrayEquals(new byte[] { 0x7f }, createVarInt(127));
        Assert.assertArrayEquals(new byte[] { (byte) 0x80, 0x01 }, createVarInt(128));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, 0x01 }, createVarInt(255));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x07 }, createVarInt(2147483647));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x0f }, createVarInt(-1));
        Assert.assertArrayEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, 0x08 }, createVarInt(-2147483648));
    }

    @Test
    public void testWriteReadVarInt() {
        ByteBuf buffer = Unpooled.buffer();
        ProtocolUtils.writeVarInt(buffer, 0);
        Assert.assertEquals(0, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 1);
        Assert.assertEquals(1, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 2);
        Assert.assertEquals(2, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 127);
        Assert.assertEquals(127, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 128);
        Assert.assertEquals(128, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 255);
        Assert.assertEquals(255, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, 2147483647);
        Assert.assertEquals(2147483647, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, -1);
        Assert.assertEquals(-1, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarInt(buffer, -2147483648);
        Assert.assertEquals(-2147483648, ProtocolUtils.readVarInt(buffer));
    }

    @Test
    public void testWriteReadVarIntArray() {
        ByteBuf buffer = Unpooled.buffer();
        int[] values = new int[] { 0, 1, 2, 127, 128, 255, 2147483647, -1, -2147483648 };
        ProtocolUtils.writeVarIntArray(buffer, values);
        Assert.assertArrayEquals(values, ProtocolUtils.readVarIntArray(buffer));
    }

    @Test
    public void testWriteReadByteArray() {
        ByteBuf buffer = Unpooled.buffer();
        byte[] values = new byte[] { 0, 1, 2, Byte.MAX_VALUE, -1, Byte.MIN_VALUE };
        ProtocolUtils.writeByteArray(buffer, values);
        Assert.assertArrayEquals(values, ProtocolUtils.readByteArray(buffer));
    }

    @Test
    public void testWriteVarLong() {
        // Test against raw reference VarLongs of wiki.vg to ensure compatibility with Minecraft
        Assert.assertArrayEquals(new byte[] { 0x00 }, createVarLong(0));
        Assert.assertArrayEquals(new byte[] { 0x01 }, createVarLong(1));
        Assert.assertArrayEquals(new byte[] { 0x02 }, createVarLong(2));
        Assert.assertArrayEquals(new byte[] { 0x7f }, createVarLong(127));
        Assert.assertArrayEquals(new byte[] { (byte) 0x80, 0x01 }, createVarLong(128));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, 0x01 }, createVarLong(255));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x07, }, createVarLong(2147483647));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x7f }, createVarLong(9223372036854775807L));
        Assert.assertArrayEquals(new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x01 }, createVarLong(-1));
        Assert.assertArrayEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x01 }, createVarLong(-2147483648));
        Assert.assertArrayEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, 0x01 }, createVarLong(-9223372036854775808L));
    }

    @Test
    public void testWriteReadVarLong() {
        ByteBuf buffer = Unpooled.buffer();
        ProtocolUtils.writeVarInt(buffer, 0);
        Assert.assertEquals(0, ProtocolUtils.readVarInt(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 1);
        Assert.assertEquals(1, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 2);
        Assert.assertEquals(2, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 127);
        Assert.assertEquals(127, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 128);
        Assert.assertEquals(128, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 255);
        Assert.assertEquals(255, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 2147483647);
        Assert.assertEquals(2147483647, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, 9223372036854775807L);
        Assert.assertEquals(9223372036854775807L, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, -1);
        Assert.assertEquals(-1, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, -2147483648);
        Assert.assertEquals(-2147483648, ProtocolUtils.readVarLong(buffer));

        buffer.clear();
        ProtocolUtils.writeVarLong(buffer, -9223372036854775808L);
        Assert.assertEquals(-9223372036854775808L, ProtocolUtils.readVarLong(buffer));
    }

    @Test
    public void testWriteReadString() {
        ByteBuf buffer = Unpooled.buffer();
        ProtocolUtils.writeString(buffer, "Test");
        Assert.assertEquals("Test", ProtocolUtils.readString(buffer));

        buffer.clear();
        ProtocolUtils.writeString(buffer, "");
        Assert.assertEquals("", ProtocolUtils.readString(buffer));
    }

    @Test
    public void testWriteReadStringArray() {
        ByteBuf buffer = Unpooled.buffer();
        String[] value = new String[] {
                "Hello",
                "World"
        };
        ProtocolUtils.writeStringArray(buffer, value);
        Assert.assertArrayEquals(value, ProtocolUtils.readStringArray(buffer));
    }

    @Test
    public void testWriteReadUuid() {
        ByteBuf buffer = Unpooled.buffer();
        UUID value = UUID.randomUUID();
        ProtocolUtils.writeUuid(buffer, value);
        Assert.assertEquals(value, ProtocolUtils.readUuid(buffer));
    }

    private static byte[] createVarInt(int value) {
        return writeToBuffer(value, ProtocolUtils::writeVarInt);
    }

    private static byte[] createVarLong(long value) {
        return writeToBuffer(value, ProtocolUtils::writeVarLong);
    }

    private static <T> byte[] writeToBuffer(T value, BiConsumer<ByteBuf, T> method) {
        ByteBuf buffer = Unpooled.buffer();
        method.accept(buffer, value);
        byte[] result = new byte[buffer.readableBytes()];
        buffer.readBytes(result);
        return result;
    }
}
