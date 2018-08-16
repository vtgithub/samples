package dto;

public final class NotificationProto {
  private NotificationProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface NotifOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Notif)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string title = 1;</code>
     */
    String getTitle();
    /**
     * <code>string title = 1;</code>
     */
    com.google.protobuf.ByteString
        getTitleBytes();

    /**
     * <code>string subtitle = 2;</code>
     */
    String getSubtitle();
    /**
     * <code>string subtitle = 2;</code>
     */
    com.google.protobuf.ByteString
        getSubtitleBytes();

    /**
     * <code>string message = 3;</code>
     */
    String getMessage();
    /**
     * <code>string message = 3;</code>
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    /**
     * <code>repeated string segments = 4;</code>
     */
    java.util.List<String>
        getSegmentsList();
    /**
     * <code>repeated string segments = 4;</code>
     */
    int getSegmentsCount();
    /**
     * <code>repeated string segments = 4;</code>
     */
    String getSegments(int index);
    /**
     * <code>repeated string segments = 4;</code>
     */
    com.google.protobuf.ByteString
        getSegmentsBytes(int index);

    /**
     * <code>int32 platform = 5;</code>
     */
    int getPlatform();
  }
  /**
   * Protobuf type {@code Notif}
   */
  public  static final class Notif extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Notif)
      NotifOrBuilder {
    // Use Notif.newBuilder() to construct.
    private Notif(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Notif() {
      title_ = "";
      subtitle_ = "";
      message_ = "";
      segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      platform_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Notif(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              String s = input.readStringRequireUtf8();

              title_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              subtitle_ = s;
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              message_ = s;
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
                segments_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000008;
              }
              segments_.add(s);
              break;
            }
            case 40: {

              platform_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
          segments_ = segments_.getUnmodifiableView();
        }
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return NotificationProto.internal_static_Notif_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return NotificationProto.internal_static_Notif_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              NotificationProto.Notif.class, NotificationProto.Notif.Builder.class);
    }

    private int bitField0_;
    public static final int TITLE_FIELD_NUMBER = 1;
    private volatile Object title_;
    /**
     * <code>string title = 1;</code>
     */
    public String getTitle() {
      Object ref = title_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        title_ = s;
        return s;
      }
    }
    /**
     * <code>string title = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTitleBytes() {
      Object ref = title_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        title_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SUBTITLE_FIELD_NUMBER = 2;
    private volatile Object subtitle_;
    /**
     * <code>string subtitle = 2;</code>
     */
    public String getSubtitle() {
      Object ref = subtitle_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        subtitle_ = s;
        return s;
      }
    }
    /**
     * <code>string subtitle = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSubtitleBytes() {
      Object ref = subtitle_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        subtitle_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MESSAGE_FIELD_NUMBER = 3;
    private volatile Object message_;
    /**
     * <code>string message = 3;</code>
     */
    public String getMessage() {
      Object ref = message_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        message_ = s;
        return s;
      }
    }
    /**
     * <code>string message = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SEGMENTS_FIELD_NUMBER = 4;
    private com.google.protobuf.LazyStringList segments_;
    /**
     * <code>repeated string segments = 4;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getSegmentsList() {
      return segments_;
    }
    /**
     * <code>repeated string segments = 4;</code>
     */
    public int getSegmentsCount() {
      return segments_.size();
    }
    /**
     * <code>repeated string segments = 4;</code>
     */
    public String getSegments(int index) {
      return segments_.get(index);
    }
    /**
     * <code>repeated string segments = 4;</code>
     */
    public com.google.protobuf.ByteString
        getSegmentsBytes(int index) {
      return segments_.getByteString(index);
    }

    public static final int PLATFORM_FIELD_NUMBER = 5;
    private int platform_;
    /**
     * <code>int32 platform = 5;</code>
     */
    public int getPlatform() {
      return platform_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getTitleBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, title_);
      }
      if (!getSubtitleBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, subtitle_);
      }
      if (!getMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
      }
      for (int i = 0; i < segments_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, segments_.getRaw(i));
      }
      if (platform_ != 0) {
        output.writeInt32(5, platform_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getTitleBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, title_);
      }
      if (!getSubtitleBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, subtitle_);
      }
      if (!getMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < segments_.size(); i++) {
          dataSize += computeStringSizeNoTag(segments_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getSegmentsList().size();
      }
      if (platform_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, platform_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof NotificationProto.Notif)) {
        return super.equals(obj);
      }
      NotificationProto.Notif other = (NotificationProto.Notif) obj;

      boolean result = true;
      result = result && getTitle()
          .equals(other.getTitle());
      result = result && getSubtitle()
          .equals(other.getSubtitle());
      result = result && getMessage()
          .equals(other.getMessage());
      result = result && getSegmentsList()
          .equals(other.getSegmentsList());
      result = result && (getPlatform()
          == other.getPlatform());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TITLE_FIELD_NUMBER;
      hash = (53 * hash) + getTitle().hashCode();
      hash = (37 * hash) + SUBTITLE_FIELD_NUMBER;
      hash = (53 * hash) + getSubtitle().hashCode();
      hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getMessage().hashCode();
      if (getSegmentsCount() > 0) {
        hash = (37 * hash) + SEGMENTS_FIELD_NUMBER;
        hash = (53 * hash) + getSegmentsList().hashCode();
      }
      hash = (37 * hash) + PLATFORM_FIELD_NUMBER;
      hash = (53 * hash) + getPlatform();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static NotificationProto.Notif parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Notif parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Notif parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Notif parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Notif parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Notif parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Notif parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.Notif parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.Notif parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static NotificationProto.Notif parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.Notif parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.Notif parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(NotificationProto.Notif prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Notif}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Notif)
        NotificationProto.NotifOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return NotificationProto.internal_static_Notif_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return NotificationProto.internal_static_Notif_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                NotificationProto.Notif.class, NotificationProto.Notif.Builder.class);
      }

      // Construct using NotificationProto.Notif.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        title_ = "";

        subtitle_ = "";

        message_ = "";

        segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        platform_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return NotificationProto.internal_static_Notif_descriptor;
      }

      public NotificationProto.Notif getDefaultInstanceForType() {
        return NotificationProto.Notif.getDefaultInstance();
      }

      public NotificationProto.Notif build() {
        NotificationProto.Notif result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public NotificationProto.Notif buildPartial() {
        NotificationProto.Notif result = new NotificationProto.Notif(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.title_ = title_;
        result.subtitle_ = subtitle_;
        result.message_ = message_;
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          segments_ = segments_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.segments_ = segments_;
        result.platform_ = platform_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof NotificationProto.Notif) {
          return mergeFrom((NotificationProto.Notif)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(NotificationProto.Notif other) {
        if (other == NotificationProto.Notif.getDefaultInstance()) return this;
        if (!other.getTitle().isEmpty()) {
          title_ = other.title_;
          onChanged();
        }
        if (!other.getSubtitle().isEmpty()) {
          subtitle_ = other.subtitle_;
          onChanged();
        }
        if (!other.getMessage().isEmpty()) {
          message_ = other.message_;
          onChanged();
        }
        if (!other.segments_.isEmpty()) {
          if (segments_.isEmpty()) {
            segments_ = other.segments_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureSegmentsIsMutable();
            segments_.addAll(other.segments_);
          }
          onChanged();
        }
        if (other.getPlatform() != 0) {
          setPlatform(other.getPlatform());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        NotificationProto.Notif parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (NotificationProto.Notif) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private Object title_ = "";
      /**
       * <code>string title = 1;</code>
       */
      public String getTitle() {
        Object ref = title_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          title_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string title = 1;</code>
       */
      public com.google.protobuf.ByteString
          getTitleBytes() {
        Object ref = title_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          title_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string title = 1;</code>
       */
      public Builder setTitle(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        title_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string title = 1;</code>
       */
      public Builder clearTitle() {

        title_ = getDefaultInstance().getTitle();
        onChanged();
        return this;
      }
      /**
       * <code>string title = 1;</code>
       */
      public Builder setTitleBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        title_ = value;
        onChanged();
        return this;
      }

      private Object subtitle_ = "";
      /**
       * <code>string subtitle = 2;</code>
       */
      public String getSubtitle() {
        Object ref = subtitle_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          subtitle_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string subtitle = 2;</code>
       */
      public com.google.protobuf.ByteString
          getSubtitleBytes() {
        Object ref = subtitle_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          subtitle_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string subtitle = 2;</code>
       */
      public Builder setSubtitle(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        subtitle_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string subtitle = 2;</code>
       */
      public Builder clearSubtitle() {

        subtitle_ = getDefaultInstance().getSubtitle();
        onChanged();
        return this;
      }
      /**
       * <code>string subtitle = 2;</code>
       */
      public Builder setSubtitleBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        subtitle_ = value;
        onChanged();
        return this;
      }

      private Object message_ = "";
      /**
       * <code>string message = 3;</code>
       */
      public String getMessage() {
        Object ref = message_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string message = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder setMessage(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder clearMessage() {

        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        message_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureSegmentsIsMutable() {
        if (!((bitField0_ & 0x00000008) == 0x00000008)) {
          segments_ = new com.google.protobuf.LazyStringArrayList(segments_);
          bitField0_ |= 0x00000008;
         }
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getSegmentsList() {
        return segments_.getUnmodifiableView();
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public int getSegmentsCount() {
        return segments_.size();
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public String getSegments(int index) {
        return segments_.get(index);
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public com.google.protobuf.ByteString
          getSegmentsBytes(int index) {
        return segments_.getByteString(index);
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public Builder setSegments(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSegmentsIsMutable();
        segments_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public Builder addSegments(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSegmentsIsMutable();
        segments_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public Builder addAllSegments(
          Iterable<String> values) {
        ensureSegmentsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, segments_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public Builder clearSegments() {
        segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 4;</code>
       */
      public Builder addSegmentsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureSegmentsIsMutable();
        segments_.add(value);
        onChanged();
        return this;
      }

      private int platform_ ;
      /**
       * <code>int32 platform = 5;</code>
       */
      public int getPlatform() {
        return platform_;
      }
      /**
       * <code>int32 platform = 5;</code>
       */
      public Builder setPlatform(int value) {

        platform_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 platform = 5;</code>
       */
      public Builder clearPlatform() {

        platform_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:Notif)
    }

    // @@protoc_insertion_point(class_scope:Notif)
    private static final NotificationProto.Notif DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new NotificationProto.Notif();
    }

    public static NotificationProto.Notif getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Notif>
        PARSER = new com.google.protobuf.AbstractParser<Notif>() {
      public Notif parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Notif(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Notif> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<Notif> getParserForType() {
      return PARSER;
    }

    public NotificationProto.Notif getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface ContentUpdateNotifOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ContentUpdateNotif)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 type = 1;</code>
     */
    int getType();

    /**
     * <code>int32 event = 2;</code>
     */
    int getEvent();

    /**
     * <code>int32 priority = 3;</code>
     */
    int getPriority();

    /**
     * <code>string id = 4;</code>
     */
    String getId();
    /**
     * <code>string id = 4;</code>
     */
    com.google.protobuf.ByteString
        getIdBytes();

    /**
     * <code>repeated string segments = 5;</code>
     */
    java.util.List<String>
        getSegmentsList();
    /**
     * <code>repeated string segments = 5;</code>
     */
    int getSegmentsCount();
    /**
     * <code>repeated string segments = 5;</code>
     */
    String getSegments(int index);
    /**
     * <code>repeated string segments = 5;</code>
     */
    com.google.protobuf.ByteString
        getSegmentsBytes(int index);

    /**
     * <code>int32 platform = 6;</code>
     */
    int getPlatform();
  }
  /**
   * Protobuf type {@code ContentUpdateNotif}
   */
  public  static final class ContentUpdateNotif extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ContentUpdateNotif)
      ContentUpdateNotifOrBuilder {
    // Use ContentUpdateNotif.newBuilder() to construct.
    private ContentUpdateNotif(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ContentUpdateNotif() {
      type_ = 0;
      event_ = 0;
      priority_ = 0;
      id_ = "";
      segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      platform_ = 0;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private ContentUpdateNotif(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              type_ = input.readInt32();
              break;
            }
            case 16: {

              event_ = input.readInt32();
              break;
            }
            case 24: {

              priority_ = input.readInt32();
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              id_ = s;
              break;
            }
            case 42: {
              String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
                segments_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000010;
              }
              segments_.add(s);
              break;
            }
            case 48: {

              platform_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
          segments_ = segments_.getUnmodifiableView();
        }
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return NotificationProto.internal_static_ContentUpdateNotif_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return NotificationProto.internal_static_ContentUpdateNotif_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              NotificationProto.ContentUpdateNotif.class, NotificationProto.ContentUpdateNotif.Builder.class);
    }

    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int EVENT_FIELD_NUMBER = 2;
    private int event_;
    /**
     * <code>int32 event = 2;</code>
     */
    public int getEvent() {
      return event_;
    }

    public static final int PRIORITY_FIELD_NUMBER = 3;
    private int priority_;
    /**
     * <code>int32 priority = 3;</code>
     */
    public int getPriority() {
      return priority_;
    }

    public static final int ID_FIELD_NUMBER = 4;
    private volatile Object id_;
    /**
     * <code>string id = 4;</code>
     */
    public String getId() {
      Object ref = id_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        id_ = s;
        return s;
      }
    }
    /**
     * <code>string id = 4;</code>
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SEGMENTS_FIELD_NUMBER = 5;
    private com.google.protobuf.LazyStringList segments_;
    /**
     * <code>repeated string segments = 5;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getSegmentsList() {
      return segments_;
    }
    /**
     * <code>repeated string segments = 5;</code>
     */
    public int getSegmentsCount() {
      return segments_.size();
    }
    /**
     * <code>repeated string segments = 5;</code>
     */
    public String getSegments(int index) {
      return segments_.get(index);
    }
    /**
     * <code>repeated string segments = 5;</code>
     */
    public com.google.protobuf.ByteString
        getSegmentsBytes(int index) {
      return segments_.getByteString(index);
    }

    public static final int PLATFORM_FIELD_NUMBER = 6;
    private int platform_;
    /**
     * <code>int32 platform = 6;</code>
     */
    public int getPlatform() {
      return platform_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeInt32(1, type_);
      }
      if (event_ != 0) {
        output.writeInt32(2, event_);
      }
      if (priority_ != 0) {
        output.writeInt32(3, priority_);
      }
      if (!getIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, id_);
      }
      for (int i = 0; i < segments_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, segments_.getRaw(i));
      }
      if (platform_ != 0) {
        output.writeInt32(6, platform_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, type_);
      }
      if (event_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, event_);
      }
      if (priority_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, priority_);
      }
      if (!getIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, id_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < segments_.size(); i++) {
          dataSize += computeStringSizeNoTag(segments_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getSegmentsList().size();
      }
      if (platform_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, platform_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof NotificationProto.ContentUpdateNotif)) {
        return super.equals(obj);
      }
      NotificationProto.ContentUpdateNotif other = (NotificationProto.ContentUpdateNotif) obj;

      boolean result = true;
      result = result && (getType()
          == other.getType());
      result = result && (getEvent()
          == other.getEvent());
      result = result && (getPriority()
          == other.getPriority());
      result = result && getId()
          .equals(other.getId());
      result = result && getSegmentsList()
          .equals(other.getSegmentsList());
      result = result && (getPlatform()
          == other.getPlatform());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (37 * hash) + EVENT_FIELD_NUMBER;
      hash = (53 * hash) + getEvent();
      hash = (37 * hash) + PRIORITY_FIELD_NUMBER;
      hash = (53 * hash) + getPriority();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
      if (getSegmentsCount() > 0) {
        hash = (37 * hash) + SEGMENTS_FIELD_NUMBER;
        hash = (53 * hash) + getSegmentsList().hashCode();
      }
      hash = (37 * hash) + PLATFORM_FIELD_NUMBER;
      hash = (53 * hash) + getPlatform();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static NotificationProto.ContentUpdateNotif parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.ContentUpdateNotif parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static NotificationProto.ContentUpdateNotif parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.ContentUpdateNotif parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(NotificationProto.ContentUpdateNotif prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ContentUpdateNotif}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ContentUpdateNotif)
        NotificationProto.ContentUpdateNotifOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return NotificationProto.internal_static_ContentUpdateNotif_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return NotificationProto.internal_static_ContentUpdateNotif_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                NotificationProto.ContentUpdateNotif.class, NotificationProto.ContentUpdateNotif.Builder.class);
      }

      // Construct using NotificationProto.ContentUpdateNotif.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        type_ = 0;

        event_ = 0;

        priority_ = 0;

        id_ = "";

        segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000010);
        platform_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return NotificationProto.internal_static_ContentUpdateNotif_descriptor;
      }

      public NotificationProto.ContentUpdateNotif getDefaultInstanceForType() {
        return NotificationProto.ContentUpdateNotif.getDefaultInstance();
      }

      public NotificationProto.ContentUpdateNotif build() {
        NotificationProto.ContentUpdateNotif result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public NotificationProto.ContentUpdateNotif buildPartial() {
        NotificationProto.ContentUpdateNotif result = new NotificationProto.ContentUpdateNotif(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.type_ = type_;
        result.event_ = event_;
        result.priority_ = priority_;
        result.id_ = id_;
        if (((bitField0_ & 0x00000010) == 0x00000010)) {
          segments_ = segments_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000010);
        }
        result.segments_ = segments_;
        result.platform_ = platform_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof NotificationProto.ContentUpdateNotif) {
          return mergeFrom((NotificationProto.ContentUpdateNotif)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(NotificationProto.ContentUpdateNotif other) {
        if (other == NotificationProto.ContentUpdateNotif.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getEvent() != 0) {
          setEvent(other.getEvent());
        }
        if (other.getPriority() != 0) {
          setPriority(other.getPriority());
        }
        if (!other.getId().isEmpty()) {
          id_ = other.id_;
          onChanged();
        }
        if (!other.segments_.isEmpty()) {
          if (segments_.isEmpty()) {
            segments_ = other.segments_;
            bitField0_ = (bitField0_ & ~0x00000010);
          } else {
            ensureSegmentsIsMutable();
            segments_.addAll(other.segments_);
          }
          onChanged();
        }
        if (other.getPlatform() != 0) {
          setPlatform(other.getPlatform());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        NotificationProto.ContentUpdateNotif parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (NotificationProto.ContentUpdateNotif) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int type_ ;
      /**
       * <code>int32 type = 1;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder setType(int value) {

        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder clearType() {

        type_ = 0;
        onChanged();
        return this;
      }

      private int event_ ;
      /**
       * <code>int32 event = 2;</code>
       */
      public int getEvent() {
        return event_;
      }
      /**
       * <code>int32 event = 2;</code>
       */
      public Builder setEvent(int value) {

        event_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 event = 2;</code>
       */
      public Builder clearEvent() {

        event_ = 0;
        onChanged();
        return this;
      }

      private int priority_ ;
      /**
       * <code>int32 priority = 3;</code>
       */
      public int getPriority() {
        return priority_;
      }
      /**
       * <code>int32 priority = 3;</code>
       */
      public Builder setPriority(int value) {

        priority_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 priority = 3;</code>
       */
      public Builder clearPriority() {

        priority_ = 0;
        onChanged();
        return this;
      }

      private Object id_ = "";
      /**
       * <code>string id = 4;</code>
       */
      public String getId() {
        Object ref = id_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string id = 4;</code>
       */
      public com.google.protobuf.ByteString
          getIdBytes() {
        Object ref = id_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          id_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string id = 4;</code>
       */
      public Builder setId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string id = 4;</code>
       */
      public Builder clearId() {

        id_ = getDefaultInstance().getId();
        onChanged();
        return this;
      }
      /**
       * <code>string id = 4;</code>
       */
      public Builder setIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        id_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureSegmentsIsMutable() {
        if (!((bitField0_ & 0x00000010) == 0x00000010)) {
          segments_ = new com.google.protobuf.LazyStringArrayList(segments_);
          bitField0_ |= 0x00000010;
         }
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getSegmentsList() {
        return segments_.getUnmodifiableView();
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public int getSegmentsCount() {
        return segments_.size();
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public String getSegments(int index) {
        return segments_.get(index);
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public com.google.protobuf.ByteString
          getSegmentsBytes(int index) {
        return segments_.getByteString(index);
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public Builder setSegments(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSegmentsIsMutable();
        segments_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public Builder addSegments(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureSegmentsIsMutable();
        segments_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public Builder addAllSegments(
          Iterable<String> values) {
        ensureSegmentsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, segments_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public Builder clearSegments() {
        segments_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000010);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string segments = 5;</code>
       */
      public Builder addSegmentsBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureSegmentsIsMutable();
        segments_.add(value);
        onChanged();
        return this;
      }

      private int platform_ ;
      /**
       * <code>int32 platform = 6;</code>
       */
      public int getPlatform() {
        return platform_;
      }
      /**
       * <code>int32 platform = 6;</code>
       */
      public Builder setPlatform(int value) {

        platform_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 platform = 6;</code>
       */
      public Builder clearPlatform() {

        platform_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:ContentUpdateNotif)
    }

    // @@protoc_insertion_point(class_scope:ContentUpdateNotif)
    private static final NotificationProto.ContentUpdateNotif DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new NotificationProto.ContentUpdateNotif();
    }

    public static NotificationProto.ContentUpdateNotif getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ContentUpdateNotif>
        PARSER = new com.google.protobuf.AbstractParser<ContentUpdateNotif>() {
      public ContentUpdateNotif parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ContentUpdateNotif(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ContentUpdateNotif> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<ContentUpdateNotif> getParserForType() {
      return PARSER;
    }

    public NotificationProto.ContentUpdateNotif getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface SMSOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SMS)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string destinationPhoneNumber = 1;</code>
     */
    String getDestinationPhoneNumber();
    /**
     * <code>string destinationPhoneNumber = 1;</code>
     */
    com.google.protobuf.ByteString
        getDestinationPhoneNumberBytes();

    /**
     * <code>string body = 2;</code>
     */
    String getBody();
    /**
     * <code>string body = 2;</code>
     */
    com.google.protobuf.ByteString
        getBodyBytes();
  }
  /**
   * Protobuf type {@code SMS}
   */
  public  static final class SMS extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SMS)
      SMSOrBuilder {
    // Use SMS.newBuilder() to construct.
    private SMS(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SMS() {
      destinationPhoneNumber_ = "";
      body_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private SMS(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              String s = input.readStringRequireUtf8();

              destinationPhoneNumber_ = s;
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              body_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return NotificationProto.internal_static_SMS_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return NotificationProto.internal_static_SMS_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              NotificationProto.SMS.class, NotificationProto.SMS.Builder.class);
    }

    public static final int DESTINATIONPHONENUMBER_FIELD_NUMBER = 1;
    private volatile Object destinationPhoneNumber_;
    /**
     * <code>string destinationPhoneNumber = 1;</code>
     */
    public String getDestinationPhoneNumber() {
      Object ref = destinationPhoneNumber_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        destinationPhoneNumber_ = s;
        return s;
      }
    }
    /**
     * <code>string destinationPhoneNumber = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDestinationPhoneNumberBytes() {
      Object ref = destinationPhoneNumber_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        destinationPhoneNumber_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int BODY_FIELD_NUMBER = 2;
    private volatile Object body_;
    /**
     * <code>string body = 2;</code>
     */
    public String getBody() {
      Object ref = body_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        body_ = s;
        return s;
      }
    }
    /**
     * <code>string body = 2;</code>
     */
    public com.google.protobuf.ByteString
        getBodyBytes() {
      Object ref = body_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        body_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getDestinationPhoneNumberBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, destinationPhoneNumber_);
      }
      if (!getBodyBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, body_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getDestinationPhoneNumberBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, destinationPhoneNumber_);
      }
      if (!getBodyBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, body_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof NotificationProto.SMS)) {
        return super.equals(obj);
      }
      NotificationProto.SMS other = (NotificationProto.SMS) obj;

      boolean result = true;
      result = result && getDestinationPhoneNumber()
          .equals(other.getDestinationPhoneNumber());
      result = result && getBody()
          .equals(other.getBody());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + DESTINATIONPHONENUMBER_FIELD_NUMBER;
      hash = (53 * hash) + getDestinationPhoneNumber().hashCode();
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static NotificationProto.SMS parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.SMS parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.SMS parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.SMS parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.SMS parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.SMS parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.SMS parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.SMS parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.SMS parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static NotificationProto.SMS parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.SMS parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.SMS parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(NotificationProto.SMS prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SMS}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SMS)
        NotificationProto.SMSOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return NotificationProto.internal_static_SMS_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return NotificationProto.internal_static_SMS_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                NotificationProto.SMS.class, NotificationProto.SMS.Builder.class);
      }

      // Construct using NotificationProto.SMS.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        destinationPhoneNumber_ = "";

        body_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return NotificationProto.internal_static_SMS_descriptor;
      }

      public NotificationProto.SMS getDefaultInstanceForType() {
        return NotificationProto.SMS.getDefaultInstance();
      }

      public NotificationProto.SMS build() {
        NotificationProto.SMS result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public NotificationProto.SMS buildPartial() {
        NotificationProto.SMS result = new NotificationProto.SMS(this);
        result.destinationPhoneNumber_ = destinationPhoneNumber_;
        result.body_ = body_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof NotificationProto.SMS) {
          return mergeFrom((NotificationProto.SMS)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(NotificationProto.SMS other) {
        if (other == NotificationProto.SMS.getDefaultInstance()) return this;
        if (!other.getDestinationPhoneNumber().isEmpty()) {
          destinationPhoneNumber_ = other.destinationPhoneNumber_;
          onChanged();
        }
        if (!other.getBody().isEmpty()) {
          body_ = other.body_;
          onChanged();
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        NotificationProto.SMS parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (NotificationProto.SMS) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object destinationPhoneNumber_ = "";
      /**
       * <code>string destinationPhoneNumber = 1;</code>
       */
      public String getDestinationPhoneNumber() {
        Object ref = destinationPhoneNumber_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          destinationPhoneNumber_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string destinationPhoneNumber = 1;</code>
       */
      public com.google.protobuf.ByteString
          getDestinationPhoneNumberBytes() {
        Object ref = destinationPhoneNumber_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          destinationPhoneNumber_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string destinationPhoneNumber = 1;</code>
       */
      public Builder setDestinationPhoneNumber(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        destinationPhoneNumber_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string destinationPhoneNumber = 1;</code>
       */
      public Builder clearDestinationPhoneNumber() {

        destinationPhoneNumber_ = getDefaultInstance().getDestinationPhoneNumber();
        onChanged();
        return this;
      }
      /**
       * <code>string destinationPhoneNumber = 1;</code>
       */
      public Builder setDestinationPhoneNumberBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        destinationPhoneNumber_ = value;
        onChanged();
        return this;
      }

      private Object body_ = "";
      /**
       * <code>string body = 2;</code>
       */
      public String getBody() {
        Object ref = body_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          body_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string body = 2;</code>
       */
      public com.google.protobuf.ByteString
          getBodyBytes() {
        Object ref = body_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          body_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string body = 2;</code>
       */
      public Builder setBody(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string body = 2;</code>
       */
      public Builder clearBody() {

        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      /**
       * <code>string body = 2;</code>
       */
      public Builder setBodyBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        body_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:SMS)
    }

    // @@protoc_insertion_point(class_scope:SMS)
    private static final NotificationProto.SMS DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new NotificationProto.SMS();
    }

    public static NotificationProto.SMS getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SMS>
        PARSER = new com.google.protobuf.AbstractParser<SMS>() {
      public SMS parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new SMS(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SMS> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<SMS> getParserForType() {
      return PARSER;
    }

    public NotificationProto.SMS getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface RequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Request)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 type = 1;</code>
     */
    int getType();

    /**
     * <code>bytes body = 2;</code>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code Request}
   */
  public  static final class Request extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Request)
      RequestOrBuilder {
    // Use Request.newBuilder() to construct.
    private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Request() {
      type_ = 0;
      body_ = com.google.protobuf.ByteString.EMPTY;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private Request(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!input.skipField(tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              type_ = input.readInt32();
              break;
            }
            case 18: {

              body_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return NotificationProto.internal_static_Request_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return NotificationProto.internal_static_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              NotificationProto.Request.class, NotificationProto.Request.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;
    /**
     * <code>int32 type = 1;</code>
     */
    public int getType() {
      return type_;
    }

    public static final int BODY_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>bytes body = 2;</code>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (type_ != 0) {
        output.writeInt32(1, type_);
      }
      if (!body_.isEmpty()) {
        output.writeBytes(2, body_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (type_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, type_);
      }
      if (!body_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, body_);
      }
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof NotificationProto.Request)) {
        return super.equals(obj);
      }
      NotificationProto.Request other = (NotificationProto.Request) obj;

      boolean result = true;
      result = result && (getType()
          == other.getType());
      result = result && getBody()
          .equals(other.getBody());
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static NotificationProto.Request parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Request parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Request parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Request parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Request parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static NotificationProto.Request parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static NotificationProto.Request parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.Request parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.Request parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static NotificationProto.Request parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static NotificationProto.Request parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static NotificationProto.Request parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(NotificationProto.Request prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Request}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Request)
        NotificationProto.RequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return NotificationProto.internal_static_Request_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return NotificationProto.internal_static_Request_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                NotificationProto.Request.class, NotificationProto.Request.Builder.class);
      }

      // Construct using NotificationProto.Request.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        type_ = 0;

        body_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return NotificationProto.internal_static_Request_descriptor;
      }

      public NotificationProto.Request getDefaultInstanceForType() {
        return NotificationProto.Request.getDefaultInstance();
      }

      public NotificationProto.Request build() {
        NotificationProto.Request result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public NotificationProto.Request buildPartial() {
        NotificationProto.Request result = new NotificationProto.Request(this);
        result.type_ = type_;
        result.body_ = body_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof NotificationProto.Request) {
          return mergeFrom((NotificationProto.Request)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(NotificationProto.Request other) {
        if (other == NotificationProto.Request.getDefaultInstance()) return this;
        if (other.getType() != 0) {
          setType(other.getType());
        }
        if (other.getBody() != com.google.protobuf.ByteString.EMPTY) {
          setBody(other.getBody());
        }
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        NotificationProto.Request parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (NotificationProto.Request) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int type_ ;
      /**
       * <code>int32 type = 1;</code>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder setType(int value) {

        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 type = 1;</code>
       */
      public Builder clearType() {

        type_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes body = 2;</code>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>bytes body = 2;</code>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }

        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes body = 2;</code>
       */
      public Builder clearBody() {

        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:Request)
    }

    // @@protoc_insertion_point(class_scope:Request)
    private static final NotificationProto.Request DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new NotificationProto.Request();
    }

    public static NotificationProto.Request getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Request>
        PARSER = new com.google.protobuf.AbstractParser<Request>() {
      public Request parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Request(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Request> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<Request> getParserForType() {
      return PARSER;
    }

    public NotificationProto.Request getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Notif_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Notif_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ContentUpdateNotif_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ContentUpdateNotif_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SMS_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SMS_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Request_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Request_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\027NotificationProto.proto\"]\n\005Notif\022\r\n\005ti" +
      "tle\030\001 \001(\t\022\020\n\010subtitle\030\002 \001(\t\022\017\n\007message\030\003" +
      " \001(\t\022\020\n\010segments\030\004 \003(\t\022\020\n\010platform\030\005 \001(\005" +
      "\"s\n\022ContentUpdateNotif\022\014\n\004type\030\001 \001(\005\022\r\n\005" +
      "event\030\002 \001(\005\022\020\n\010priority\030\003 \001(\005\022\n\n\002id\030\004 \001(" +
      "\t\022\020\n\010segments\030\005 \003(\t\022\020\n\010platform\030\006 \001(\005\"3\n" +
      "\003SMS\022\036\n\026destinationPhoneNumber\030\001 \001(\t\022\014\n\004" +
      "body\030\002 \001(\t\"%\n\007Request\022\014\n\004type\030\001 \001(\005\022\014\n\004b" +
      "ody\030\002 \001(\014b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Notif_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Notif_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Notif_descriptor,
        new String[] { "Title", "Subtitle", "Message", "Segments", "Platform", });
    internal_static_ContentUpdateNotif_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ContentUpdateNotif_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ContentUpdateNotif_descriptor,
        new String[] { "Type", "Event", "Priority", "Id", "Segments", "Platform", });
    internal_static_SMS_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_SMS_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SMS_descriptor,
        new String[] { "DestinationPhoneNumber", "Body", });
    internal_static_Request_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Request_descriptor,
        new String[] { "Type", "Body", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
