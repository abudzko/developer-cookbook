package com.budzko.cookbook.java.study.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableStudy {
    public static void main(String[] args) throws Exception {

        SerializableModel serializableModel = new SerializableModel();
        serializableModel.x = 1;
        serializableModel.y = 2;
        serializableModel.z = 3;

        byte[] serializableModelBytes = writeObject(serializableModel);
        SerializableModel serializableModel1 = (SerializableModel) readObject(serializableModelBytes);

        System.out.println("before:" + serializableModel);
        System.out.println("after:" + serializableModel1);

        System.out.println();

        ExternalizableModel externalizableModel = new ExternalizableModel();
        externalizableModel.x = 1;
        externalizableModel.y = 2;
        externalizableModel.z = 3;

        byte[] externalizableModelBytes = writeObject(externalizableModel);
        ExternalizableModel externalizableModel1 = (ExternalizableModel) readObject(externalizableModelBytes);
        System.out.println("before:" + externalizableModel);
        System.out.println("after:" + externalizableModel1);

        System.out.println();

        MixedSerializableModel mixedSerializableModel = new MixedSerializableModel();
        mixedSerializableModel.x = 1;
        mixedSerializableModel.y = 2;
        mixedSerializableModel.z = 3;

        byte[] mixedSerializableModelBytes = writeObject(mixedSerializableModel);
        MixedSerializableModel mixedSerializableModel1 = (MixedSerializableModel) readObject(mixedSerializableModelBytes);
        System.out.println("before:" + mixedSerializableModel1);
        System.out.println("after:" + mixedSerializableModel1);
    }

    private static Object readObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object o = objectInputStream.readObject();
        objectInputStream.close();
        return o;
    }

    private static byte[] writeObject(Object model) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(model);
        objectOutputStream.flush();
        objectOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}

class SerializableModel implements Serializable {
    int x;
    transient int y;
    int z;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": x = " + x + ", y = " + y + ", z = " + z;
    }
}

class ExternalizableModel implements Externalizable {
    int x;
    transient int y;
    int z;

    public ExternalizableModel() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        x = -1;
        System.out.println("writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = -11;
        System.out.println("readExternal");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": x = " + x + ", y = " + y + ", z = " + z;
    }
}

/**
 * Serialization mechanism calls 'writeObject' and 'readObject' methods instead of default behavior for Serializable interface
 */
class MixedSerializableModel implements Serializable {
    int x;
    transient int y;
    int z;

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeInt(x);
        stream.writeInt(y);

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        this.x = stream.readInt();
        this.y = stream.readInt();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": x = " + x + ", y = " + y + ", z = " + z;
    }
}