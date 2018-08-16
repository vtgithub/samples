package ir.ord.application.dto.protoes;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TestContainerDto.proto

public final class TestContainerDto {
  private TestContainerDto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TestContainerOrBuilder extends
      // @@protoc_insertion_point(interface_extends:TestContainer)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 age = 1;</code>
     */
    int getAge();

    /**
     * <code>string name = 2;</code>
     */
    String getName();
    /**
     * <code>string name = 2;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>.Test testObj = 3;</code>
     */
    boolean hasTestObj();
    /**
     * <code>.Test testObj = 3;</code>
     */
    TestDto.Test getTestObj();
    /**
     * <code>.Test testObj = 3;</code>
     */
    TestDto.TestOrBuilder getTestObjOrBuilder();
  }
  /**
   * Protobuf type {@code TestContainer}
   */
  public  static final class TestContainer extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:TestContainer)
      TestContainerOrBuilder {
    // Use TestContainer.newBuilder() to construct.
    private TestContainer(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private TestContainer() {
      age_ = 0;
      name_ = "";
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private TestContainer(
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

              age_ = input.readInt32();
              break;
            }
            case 18: {
              String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 26: {
              TestDto.Test.Builder subBuilder = null;
              if (testObj_ != null) {
                subBuilder = testObj_.toBuilder();
              }
              testObj_ = input.readMessage(TestDto.Test.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(testObj_);
                testObj_ = subBuilder.buildPartial();
              }

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
      return TestContainerDto.internal_static_TestContainer_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TestContainerDto.internal_static_TestContainer_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              TestContainer.class, Builder.class);
    }

    public static final int AGE_FIELD_NUMBER = 1;
    private int age_;
    /**
     * <code>int32 age = 1;</code>
     */
    public int getAge() {
      return age_;
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private volatile Object name_;
    /**
     * <code>string name = 2;</code>
     */
    public String getName() {
      Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TESTOBJ_FIELD_NUMBER = 3;
    private TestDto.Test testObj_;
    /**
     * <code>.Test testObj = 3;</code>
     */
    public boolean hasTestObj() {
      return testObj_ != null;
    }
    /**
     * <code>.Test testObj = 3;</code>
     */
    public TestDto.Test getTestObj() {
      return testObj_ == null ? TestDto.Test.getDefaultInstance() : testObj_;
    }
    /**
     * <code>.Test testObj = 3;</code>
     */
    public TestDto.TestOrBuilder getTestObjOrBuilder() {
      return getTestObj();
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
      if (age_ != 0) {
        output.writeInt32(1, age_);
      }
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
      }
      if (testObj_ != null) {
        output.writeMessage(3, getTestObj());
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (age_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, age_);
      }
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
      }
      if (testObj_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getTestObj());
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
      if (!(obj instanceof TestContainer)) {
        return super.equals(obj);
      }
      TestContainer other = (TestContainer) obj;

      boolean result = true;
      result = result && (getAge()
          == other.getAge());
      result = result && getName()
          .equals(other.getName());
      result = result && (hasTestObj() == other.hasTestObj());
      if (hasTestObj()) {
        result = result && getTestObj()
            .equals(other.getTestObj());
      }
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + AGE_FIELD_NUMBER;
      hash = (53 * hash) + getAge();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      if (hasTestObj()) {
        hash = (37 * hash) + TESTOBJ_FIELD_NUMBER;
        hash = (53 * hash) + getTestObj().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static TestContainer parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TestContainer parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TestContainer parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TestContainer parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TestContainer parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TestContainer parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TestContainer parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static TestContainer parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static TestContainer parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static TestContainer parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static TestContainer parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static TestContainer parseFrom(
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
    public static Builder newBuilder(TestContainer prototype) {
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
     * Protobuf type {@code TestContainer}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:TestContainer)
        TestContainerOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return TestContainerDto.internal_static_TestContainer_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return TestContainerDto.internal_static_TestContainer_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                TestContainer.class, Builder.class);
      }

      // Construct using TestContainerDto.TestContainer.newBuilder()
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
        age_ = 0;

        name_ = "";

        if (testObjBuilder_ == null) {
          testObj_ = null;
        } else {
          testObj_ = null;
          testObjBuilder_ = null;
        }
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return TestContainerDto.internal_static_TestContainer_descriptor;
      }

      public TestContainer getDefaultInstanceForType() {
        return TestContainer.getDefaultInstance();
      }

      public TestContainer build() {
        TestContainer result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public TestContainer buildPartial() {
        TestContainer result = new TestContainer(this);
        result.age_ = age_;
        result.name_ = name_;
        if (testObjBuilder_ == null) {
          result.testObj_ = testObj_;
        } else {
          result.testObj_ = testObjBuilder_.build();
        }
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
        if (other instanceof TestContainer) {
          return mergeFrom((TestContainer)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(TestContainer other) {
        if (other == TestContainer.getDefaultInstance()) return this;
        if (other.getAge() != 0) {
          setAge(other.getAge());
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.hasTestObj()) {
          mergeTestObj(other.getTestObj());
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
        TestContainer parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (TestContainer) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int age_ ;
      /**
       * <code>int32 age = 1;</code>
       */
      public int getAge() {
        return age_;
      }
      /**
       * <code>int32 age = 1;</code>
       */
      public Builder setAge(int value) {
        
        age_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 age = 1;</code>
       */
      public Builder clearAge() {
        
        age_ = 0;
        onChanged();
        return this;
      }

      private Object name_ = "";
      /**
       * <code>string name = 2;</code>
       */
      public String getName() {
        Object ref = name_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder setName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private TestDto.Test testObj_ = null;
      private com.google.protobuf.SingleFieldBuilderV3<
          TestDto.Test, TestDto.Test.Builder, TestDto.TestOrBuilder> testObjBuilder_;
      /**
       * <code>.Test testObj = 3;</code>
       */
      public boolean hasTestObj() {
        return testObjBuilder_ != null || testObj_ != null;
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public TestDto.Test getTestObj() {
        if (testObjBuilder_ == null) {
          return testObj_ == null ? TestDto.Test.getDefaultInstance() : testObj_;
        } else {
          return testObjBuilder_.getMessage();
        }
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public Builder setTestObj(TestDto.Test value) {
        if (testObjBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          testObj_ = value;
          onChanged();
        } else {
          testObjBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public Builder setTestObj(
          TestDto.Test.Builder builderForValue) {
        if (testObjBuilder_ == null) {
          testObj_ = builderForValue.build();
          onChanged();
        } else {
          testObjBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public Builder mergeTestObj(TestDto.Test value) {
        if (testObjBuilder_ == null) {
          if (testObj_ != null) {
            testObj_ =
              TestDto.Test.newBuilder(testObj_).mergeFrom(value).buildPartial();
          } else {
            testObj_ = value;
          }
          onChanged();
        } else {
          testObjBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public Builder clearTestObj() {
        if (testObjBuilder_ == null) {
          testObj_ = null;
          onChanged();
        } else {
          testObj_ = null;
          testObjBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public TestDto.Test.Builder getTestObjBuilder() {
        
        onChanged();
        return getTestObjFieldBuilder().getBuilder();
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      public TestDto.TestOrBuilder getTestObjOrBuilder() {
        if (testObjBuilder_ != null) {
          return testObjBuilder_.getMessageOrBuilder();
        } else {
          return testObj_ == null ?
              TestDto.Test.getDefaultInstance() : testObj_;
        }
      }
      /**
       * <code>.Test testObj = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          TestDto.Test, TestDto.Test.Builder, TestDto.TestOrBuilder> 
          getTestObjFieldBuilder() {
        if (testObjBuilder_ == null) {
          testObjBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              TestDto.Test, TestDto.Test.Builder, TestDto.TestOrBuilder>(
                  getTestObj(),
                  getParentForChildren(),
                  isClean());
          testObj_ = null;
        }
        return testObjBuilder_;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return this;
      }


      // @@protoc_insertion_point(builder_scope:TestContainer)
    }

    // @@protoc_insertion_point(class_scope:TestContainer)
    private static final TestContainer DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new TestContainer();
    }

    public static TestContainer getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TestContainer>
        PARSER = new com.google.protobuf.AbstractParser<TestContainer>() {
      public TestContainer parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new TestContainer(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<TestContainer> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<TestContainer> getParserForType() {
      return PARSER;
    }

    public TestContainer getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TestContainer_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TestContainer_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026TestContainerDto.proto\032\rTestDto.proto\"" +
      "B\n\rTestContainer\022\013\n\003age\030\001 \001(\005\022\014\n\004name\030\002 " +
      "\001(\t\022\026\n\007testObj\030\003 \001(\0132\005.Testb\006proto3"
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
          TestDto.getDescriptor(),
        }, assigner);
    internal_static_TestContainer_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_TestContainer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TestContainer_descriptor,
        new String[] { "Age", "Name", "TestObj", });
    TestDto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
