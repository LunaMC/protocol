# Luna Protocol [![Build Status](https://travis-ci.org/LunaMC/protocol.svg?branch=master)](https://travis-ci.org/LunaMC/protocol)

Netty handlers for the Minecraft data protocol 1.8+

## Prerequisites

 * Netty 4.1
 * Java 8

## Usage

Latest release from Maven Central:

```xml
<dependency>
    <groupId>io.lunamc</groupId>
    <artifactId>luna-protocol</artifactId>
    <version>0.1.0</version>
</dependency>
```

Latest development snapshot from OSSRH:

```xml
<repository>
    <id>ossrh</id>
    <url>https://oss.sonatype.org/content/groups/public/</url>
</repository>
```

```xml
<dependency>
    <groupId>io.lunamc</groupId>
    <artifactId>luna-protocol</artifactId>
    <version>0.2.0-SNAPSHOT</version>
</dependency>
```

## Overview

 * `io.lunamc.protocol.ProtocolUtils` - Read and write Minecraft-specific data types (VarInt, VarLong, UUID, String
   and array types).
 * `io.lunamc.protocol.handler.LengthLimitedFrameDecoder` - Reads a (potentially) fragmented packet with a length up
   to 32,767 bytes.
 * `io.lunamc.protocol.handler.PacketInboundHandlerAdapter` - A base class for handling successfully decoded packets.
 * `io.lunamc.protocol.handler.PacketLengthPrepender` - Prepends the length as a VarInt in front of each outgoing
   `ByteBuf`.
 * `io.lunamc.protocol.handler.cipher.CipherDecoder` - Decrypts incoming `ByteBuf`s.
 * `io.lunamc.protocol.handler.cipher.CipherEncoder` - Encrypts outgoing `ByteBuf`s.
 * `io.lunamc.protocol.handler.compression.PacketCompressor` - Compresses outgoing `ByteBuf`s.
 * `io.lunamc.protocol.handler.compression.PacketDecompressor` - Decompress incoming `ByteBuf`s.

## Documentation

 * [JavaDoc 0.1.0](https://javadoc.io/doc/io.lunamc/luna-protocol/0.1.0)
 * [Changelog](./CHANGELOG.md)

## Special Thanks

 * [The Minecraft Coalition](http://wiki.vg/) for reverse engineering the Minecraft protocol.
