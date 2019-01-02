package com.longxin.bdt.phone;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;


public class PhoneNumberGeo {

    private static final int INDEX_SEGMENT_LENGTH = 9;
    private static final int DATA_FILE_LENGTH_HINT = 3747505;

    private byte[] dataByteArray;
    private int dataVersion;
    private int indexAreaOffset;
    private int phoneRecordCount;
    private ByteBuffer byteBuffer;

    public static PhoneNumberGeo initData(String path) throws IOException {
        PhoneNumberGeo phoneNumberGeo = new PhoneNumberGeo();

        if (phoneNumberGeo.dataByteArray == null) {
            ByteArrayOutputStream byteData = new ByteArrayOutputStream(DATA_FILE_LENGTH_HINT);
            byte[] buffer = new byte[1024];

            int readBytesLength;
            try (InputStream inputStream = PhoneNumberGeo.class.getClassLoader().getResourceAsStream(path)) {
                while ((readBytesLength = inputStream.read(buffer)) != -1) {
                    byteData.write(buffer, 0, readBytesLength);
                }
            } catch (Exception e) {
                System.err.println("Can't find phone.dat in classpath: " + path);
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            phoneNumberGeo.dataByteArray = byteData.toByteArray();

            d(phoneNumberGeo.dataByteArray);
        }
        return phoneNumberGeo;
    }

    public static void d(byte[] dataByteArray) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(dataByteArray);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        // 读取version
        String version;
        byte[] versionBytes = new byte[4];
        byteBuffer.get(versionBytes);
        version = new String(versionBytes);
        // 读取size
        int indexOffset = byteBuffer.getInt();

        byte[] dataBufferOrigin = new byte[indexOffset - 4 * 2];
        byteBuffer.get(dataBufferOrigin);

        Map<Integer, String> datas = new LinkedHashMap<>();
        ByteArrayOutputStream byteData = new ByteArrayOutputStream();
        int of = 0;
        for (int i = 0; i < dataBufferOrigin.length; i++) {
            if (dataBufferOrigin[i] == 0) {
                datas.put(of + 8, byteData.toString());
                byteData.reset();
                of = i + 1;
            } else {
                byteData.write(dataBufferOrigin[i]);
            }
        }
        datas.entrySet().forEach(System.out::println);

        List<Record> records = new ArrayList<>();
        for (int i = 0; i < (DATA_FILE_LENGTH_HINT - indexOffset) / INDEX_SEGMENT_LENGTH; i++) {
            Record record = new Record();
            int prefix = byteBuffer.getInt();
            int dataOffset = byteBuffer.getInt();
            byte[] v = new byte[1];
            byteBuffer.get(v);
            CardType cardType = CardType.getFromID(v[0]);
            String[] data = datas.get(dataOffset).split("\\|");
            record.setPhonePrefix(String.valueOf(prefix));
            record.setTelecomsOperators(cardType.getName());
            record.setProvince(data[0]);
            record.setCity(data[1]);
            record.setPostCode(data[2]);
            record.setAreaCode(data[3]);
            records.add(record);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\rebie\\Desktop\\phone\\phone.csv");
        records.forEach(s->{
            try {
                fileOutputStream.write((s.toString()+"\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileOutputStream.close();
    }

    public int getPhoneRecordCount() {
        return phoneRecordCount;
    }

    public int getDataVersion() {
        return dataVersion;
    }

}

