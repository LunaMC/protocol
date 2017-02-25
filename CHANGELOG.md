# Change Log

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to
[Semantic Versioning](http://semver.org/).

## [Unreleased]

### Added

 * An interface for reading/writing protocol model data (`Packet`)
 * An protocol model for handshake requests (`HandshakeRequestV47`)
 * An allocator for getting unpooled `HandshakeRequestV47` protocol models
 * An allocator for getting pooled `HandshakeRequestV47` protocol models

## 0.1.0 - 2017-02-16

### Added

 * Utility for read/write Minecraft-specific arrays, VarInts, VarLongs, Strings and UUIDs
 * Utility for representing a ChannelHandlerContext as a string
 * Protocol Encryption/Decryption handlers
 * Protocol Compression/Decompression handlers
 * Handler for prepending packet length as a VarInt
 * Handler for decoding framed packets by a VarInt length-prefix
 * Base class for handling incoming packets

[Unreleased]: https://github.com/LunaMC/protocol/compare/v0.1.0...HEAD
