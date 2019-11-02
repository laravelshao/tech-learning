// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.laravelshao.learning.grpc.proto;

public final class StudentProto {
  private StudentProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_MyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_MyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_MyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_MyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_StudentRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_StudentRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_StudentResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_StudentResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_StudentResponseList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_StudentResponseList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_StreamRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_StreamRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_laravelshao_learning_proto_StreamResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_laravelshao_learning_proto_StreamResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\rStudent.proto\022\036com.laravelshao.learnin" +
      "g.proto\"\035\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036" +
      "\n\nMyResponse\022\020\n\010realname\030\002 \001(\t\"\035\n\016Studen" +
      "tRequest\022\013\n\003age\030\001 \001(\005\":\n\017StudentResponse" +
      "\022\014\n\004name\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(" +
      "\t\"_\n\023StudentResponseList\022H\n\017studentRespo" +
      "nse\030\001 \003(\0132/.com.laravelshao.learning.pro" +
      "to.StudentResponse\"%\n\rStreamRequest\022\024\n\014r" +
      "equest_info\030\001 \001(\t\"\'\n\016StreamResponse\022\025\n\rr" +
      "esponse_info\030\002 \001(\t2\360\003\n\016StudentService\022p\n" +
      "\025GetRealNameByUsername\022).com.laravelshao" +
      ".learning.proto.MyRequest\032*.com.laravels" +
      "hao.learning.proto.MyResponse\"\000\022w\n\020GetSt" +
      "udentsByAge\022..com.laravelshao.learning.p" +
      "roto.StudentRequest\032/.com.laravelshao.le" +
      "arning.proto.StudentResponse\"\0000\001\022\203\001\n\030Get" +
      "StudentsWrapperByAges\022..com.laravelshao." +
      "learning.proto.StudentRequest\0323.com.lara" +
      "velshao.learning.proto.StudentResponseLi" +
      "st\"\000(\001\022m\n\006BiTalk\022-.com.laravelshao.learn" +
      "ing.proto.StreamRequest\032..com.laravelsha" +
      "o.learning.proto.StreamResponse\"\000(\0010\001B5\n" +
      "#com.laravelshao.learning.grpc.protoB\014St" +
      "udentProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_laravelshao_learning_proto_MyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_laravelshao_learning_proto_MyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_MyRequest_descriptor,
        new String[] { "Username", });
    internal_static_com_laravelshao_learning_proto_MyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_laravelshao_learning_proto_MyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_MyResponse_descriptor,
        new String[] { "Realname", });
    internal_static_com_laravelshao_learning_proto_StudentRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_laravelshao_learning_proto_StudentRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_StudentRequest_descriptor,
        new String[] { "Age", });
    internal_static_com_laravelshao_learning_proto_StudentResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_com_laravelshao_learning_proto_StudentResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_StudentResponse_descriptor,
        new String[] { "Name", "Age", "City", });
    internal_static_com_laravelshao_learning_proto_StudentResponseList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_com_laravelshao_learning_proto_StudentResponseList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_StudentResponseList_descriptor,
        new String[] { "StudentResponse", });
    internal_static_com_laravelshao_learning_proto_StreamRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_com_laravelshao_learning_proto_StreamRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_StreamRequest_descriptor,
        new String[] { "RequestInfo", });
    internal_static_com_laravelshao_learning_proto_StreamResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_com_laravelshao_learning_proto_StreamResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_laravelshao_learning_proto_StreamResponse_descriptor,
        new String[] { "ResponseInfo", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}