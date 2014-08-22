/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netflix.suro.thrift;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import java.nio.ByteBuffer;
import java.util.*;

public class TMessageSet implements org.apache.thrift.TBase<TMessageSet, TMessageSet._Fields>, java.io.Serializable, Cloneable, Comparable<TMessageSet> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TMessageSet");

  private static final org.apache.thrift.protocol.TField APP_FIELD_DESC = new org.apache.thrift.protocol.TField("app", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField NUM_MESSAGES_FIELD_DESC = new org.apache.thrift.protocol.TField("numMessages", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField COMPRESSION_FIELD_DESC = new org.apache.thrift.protocol.TField("compression", org.apache.thrift.protocol.TType.BYTE, (short)3);
  private static final org.apache.thrift.protocol.TField CRC_FIELD_DESC = new org.apache.thrift.protocol.TField("crc", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField MESSAGES_FIELD_DESC = new org.apache.thrift.protocol.TField("messages", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TMessageSetStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TMessageSetTupleSchemeFactory());
  }

  public String app; // required
  public int numMessages; // required
  public byte compression; // required
  public long crc; // required
  public ByteBuffer messages; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP((short)1, "app"),
    NUM_MESSAGES((short)2, "numMessages"),
    COMPRESSION((short)3, "compression"),
    CRC((short)4, "crc"),
    MESSAGES((short)5, "messages");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // APP
          return APP;
        case 2: // NUM_MESSAGES
          return NUM_MESSAGES;
        case 3: // COMPRESSION
          return COMPRESSION;
        case 4: // CRC
          return CRC;
        case 5: // MESSAGES
          return MESSAGES;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NUMMESSAGES_ISSET_ID = 0;
  private static final int __COMPRESSION_ISSET_ID = 1;
  private static final int __CRC_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APP, new org.apache.thrift.meta_data.FieldMetaData("app", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NUM_MESSAGES, new org.apache.thrift.meta_data.FieldMetaData("numMessages", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.COMPRESSION, new org.apache.thrift.meta_data.FieldMetaData("compression", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BYTE)));
    tmpMap.put(_Fields.CRC, new org.apache.thrift.meta_data.FieldMetaData("crc", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MESSAGES, new org.apache.thrift.meta_data.FieldMetaData("messages", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TMessageSet.class, metaDataMap);
  }

  public TMessageSet() {
  }

  public TMessageSet(
    String app,
    int numMessages,
    byte compression,
    long crc,
    ByteBuffer messages)
  {
    this();
    this.app = app;
    this.numMessages = numMessages;
    setNumMessagesIsSet(true);
    this.compression = compression;
    setCompressionIsSet(true);
    this.crc = crc;
    setCrcIsSet(true);
    this.messages = messages;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TMessageSet(TMessageSet other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetApp()) {
      this.app = other.app;
    }
    this.numMessages = other.numMessages;
    this.compression = other.compression;
    this.crc = other.crc;
    if (other.isSetMessages()) {
      this.messages = org.apache.thrift.TBaseHelper.copyBinary(other.messages);
;
    }
  }

  public TMessageSet deepCopy() {
    return new TMessageSet(this);
  }

  @Override
  public void clear() {
    this.app = null;
    setNumMessagesIsSet(false);
    this.numMessages = 0;
    setCompressionIsSet(false);
    this.compression = 0;
    setCrcIsSet(false);
    this.crc = 0;
    this.messages = null;
  }

  public String getApp() {
    return this.app;
  }

  public TMessageSet setApp(String app) {
    this.app = app;
    return this;
  }

  public void unsetApp() {
    this.app = null;
  }

  /** Returns true if field app is set (has been assigned a value) and false otherwise */
  public boolean isSetApp() {
    return this.app != null;
  }

  public void setAppIsSet(boolean value) {
    if (!value) {
      this.app = null;
    }
  }

  public int getNumMessages() {
    return this.numMessages;
  }

  public TMessageSet setNumMessages(int numMessages) {
    this.numMessages = numMessages;
    setNumMessagesIsSet(true);
    return this;
  }

  public void unsetNumMessages() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUMMESSAGES_ISSET_ID);
  }

  /** Returns true if field numMessages is set (has been assigned a value) and false otherwise */
  public boolean isSetNumMessages() {
    return EncodingUtils.testBit(__isset_bitfield, __NUMMESSAGES_ISSET_ID);
  }

  public void setNumMessagesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUMMESSAGES_ISSET_ID, value);
  }

  public byte getCompression() {
    return this.compression;
  }

  public TMessageSet setCompression(byte compression) {
    this.compression = compression;
    setCompressionIsSet(true);
    return this;
  }

  public void unsetCompression() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COMPRESSION_ISSET_ID);
  }

  /** Returns true if field compression is set (has been assigned a value) and false otherwise */
  public boolean isSetCompression() {
    return EncodingUtils.testBit(__isset_bitfield, __COMPRESSION_ISSET_ID);
  }

  public void setCompressionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COMPRESSION_ISSET_ID, value);
  }

  public long getCrc() {
    return this.crc;
  }

  public TMessageSet setCrc(long crc) {
    this.crc = crc;
    setCrcIsSet(true);
    return this;
  }

  public void unsetCrc() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CRC_ISSET_ID);
  }

  /** Returns true if field crc is set (has been assigned a value) and false otherwise */
  public boolean isSetCrc() {
    return EncodingUtils.testBit(__isset_bitfield, __CRC_ISSET_ID);
  }

  public void setCrcIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CRC_ISSET_ID, value);
  }

  public byte[] getMessages() {
    setMessages(org.apache.thrift.TBaseHelper.rightSize(messages));
    return messages == null ? null : messages.array();
  }

  public ByteBuffer bufferForMessages() {
    return messages;
  }

  public TMessageSet setMessages(byte[] messages) {
    setMessages(messages == null ? (ByteBuffer)null : ByteBuffer.wrap(messages));
    return this;
  }

  public TMessageSet setMessages(ByteBuffer messages) {
    this.messages = messages;
    return this;
  }

  public void unsetMessages() {
    this.messages = null;
  }

  /** Returns true if field messages is set (has been assigned a value) and false otherwise */
  public boolean isSetMessages() {
    return this.messages != null;
  }

  public void setMessagesIsSet(boolean value) {
    if (!value) {
      this.messages = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APP:
      if (value == null) {
        unsetApp();
      } else {
        setApp((String)value);
      }
      break;

    case NUM_MESSAGES:
      if (value == null) {
        unsetNumMessages();
      } else {
        setNumMessages((Integer)value);
      }
      break;

    case COMPRESSION:
      if (value == null) {
        unsetCompression();
      } else {
        setCompression((Byte)value);
      }
      break;

    case CRC:
      if (value == null) {
        unsetCrc();
      } else {
        setCrc((Long)value);
      }
      break;

    case MESSAGES:
      if (value == null) {
        unsetMessages();
      } else {
        setMessages((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APP:
      return getApp();

    case NUM_MESSAGES:
      return Integer.valueOf(getNumMessages());

    case COMPRESSION:
      return Byte.valueOf(getCompression());

    case CRC:
      return Long.valueOf(getCrc());

    case MESSAGES:
      return getMessages();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APP:
      return isSetApp();
    case NUM_MESSAGES:
      return isSetNumMessages();
    case COMPRESSION:
      return isSetCompression();
    case CRC:
      return isSetCrc();
    case MESSAGES:
      return isSetMessages();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TMessageSet)
      return this.equals((TMessageSet)that);
    return false;
  }

  public boolean equals(TMessageSet that) {
    if (that == null)
      return false;

    boolean this_present_app = true && this.isSetApp();
    boolean that_present_app = true && that.isSetApp();
    if (this_present_app || that_present_app) {
      if (!(this_present_app && that_present_app))
        return false;
      if (!this.app.equals(that.app))
        return false;
    }

    boolean this_present_numMessages = true;
    boolean that_present_numMessages = true;
    if (this_present_numMessages || that_present_numMessages) {
      if (!(this_present_numMessages && that_present_numMessages))
        return false;
      if (this.numMessages != that.numMessages)
        return false;
    }

    boolean this_present_compression = true;
    boolean that_present_compression = true;
    if (this_present_compression || that_present_compression) {
      if (!(this_present_compression && that_present_compression))
        return false;
      if (this.compression != that.compression)
        return false;
    }

    boolean this_present_crc = true;
    boolean that_present_crc = true;
    if (this_present_crc || that_present_crc) {
      if (!(this_present_crc && that_present_crc))
        return false;
      if (this.crc != that.crc)
        return false;
    }

    boolean this_present_messages = true && this.isSetMessages();
    boolean that_present_messages = true && that.isSetMessages();
    if (this_present_messages || that_present_messages) {
      if (!(this_present_messages && that_present_messages))
        return false;
      if (!this.messages.equals(that.messages))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TMessageSet other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetApp()).compareTo(other.isSetApp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetApp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.app, other.app);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNumMessages()).compareTo(other.isSetNumMessages());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNumMessages()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.numMessages, other.numMessages);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCompression()).compareTo(other.isSetCompression());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompression()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.compression, other.compression);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCrc()).compareTo(other.isSetCrc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCrc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.crc, other.crc);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMessages()).compareTo(other.isSetMessages());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessages()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.messages, other.messages);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TMessageSet(");
    boolean first = true;

    sb.append("app:");
    if (this.app == null) {
      sb.append("null");
    } else {
      sb.append(this.app);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("numMessages:");
    sb.append(this.numMessages);
    first = false;
    if (!first) sb.append(", ");
    sb.append("compression:");
    sb.append(this.compression);
    first = false;
    if (!first) sb.append(", ");
    sb.append("crc:");
    sb.append(this.crc);
    first = false;
    if (!first) sb.append(", ");
    sb.append("messages:");
    if (this.messages == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.messages, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TMessageSetStandardSchemeFactory implements SchemeFactory {
    public TMessageSetStandardScheme getScheme() {
      return new TMessageSetStandardScheme();
    }
  }

  private static class TMessageSetStandardScheme extends StandardScheme<TMessageSet> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TMessageSet struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.app = iprot.readString();
              struct.setAppIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NUM_MESSAGES
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.numMessages = iprot.readI32();
              struct.setNumMessagesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COMPRESSION
            if (schemeField.type == org.apache.thrift.protocol.TType.BYTE) {
              struct.compression = iprot.readByte();
              struct.setCompressionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CRC
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.crc = iprot.readI64();
              struct.setCrcIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MESSAGES
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.messages = iprot.readBinary();
              struct.setMessagesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TMessageSet struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.app != null) {
        oprot.writeFieldBegin(APP_FIELD_DESC);
        oprot.writeString(struct.app);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NUM_MESSAGES_FIELD_DESC);
      oprot.writeI32(struct.numMessages);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(COMPRESSION_FIELD_DESC);
      oprot.writeByte(struct.compression);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CRC_FIELD_DESC);
      oprot.writeI64(struct.crc);
      oprot.writeFieldEnd();
      if (struct.messages != null) {
        oprot.writeFieldBegin(MESSAGES_FIELD_DESC);
        oprot.writeBinary(struct.messages);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TMessageSetTupleSchemeFactory implements SchemeFactory {
    public TMessageSetTupleScheme getScheme() {
      return new TMessageSetTupleScheme();
    }
  }

  private static class TMessageSetTupleScheme extends TupleScheme<TMessageSet> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TMessageSet struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetApp()) {
        optionals.set(0);
      }
      if (struct.isSetNumMessages()) {
        optionals.set(1);
      }
      if (struct.isSetCompression()) {
        optionals.set(2);
      }
      if (struct.isSetCrc()) {
        optionals.set(3);
      }
      if (struct.isSetMessages()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetApp()) {
        oprot.writeString(struct.app);
      }
      if (struct.isSetNumMessages()) {
        oprot.writeI32(struct.numMessages);
      }
      if (struct.isSetCompression()) {
        oprot.writeByte(struct.compression);
      }
      if (struct.isSetCrc()) {
        oprot.writeI64(struct.crc);
      }
      if (struct.isSetMessages()) {
        oprot.writeBinary(struct.messages);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TMessageSet struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.app = iprot.readString();
        struct.setAppIsSet(true);
      }
      if (incoming.get(1)) {
        struct.numMessages = iprot.readI32();
        struct.setNumMessagesIsSet(true);
      }
      if (incoming.get(2)) {
        struct.compression = iprot.readByte();
        struct.setCompressionIsSet(true);
      }
      if (incoming.get(3)) {
        struct.crc = iprot.readI64();
        struct.setCrcIsSet(true);
      }
      if (incoming.get(4)) {
        struct.messages = iprot.readBinary();
        struct.setMessagesIsSet(true);
      }
    }
  }

}

