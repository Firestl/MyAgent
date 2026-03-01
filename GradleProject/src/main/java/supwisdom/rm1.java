package supwisdom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: compiled from: CacheDataUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class rm1 {
    public static boolean a(String str, String str2, Object obj) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return a(new File(str + File.separator + str2), obj);
    }

    public static boolean a(File file, Object obj) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byteArray = null;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        if (byteArray == null) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
            nm1.b.a(file);
            return true;
        } catch (FileNotFoundException e4) {
            e4.printStackTrace();
            return false;
        } catch (IOException e5) {
            e5.printStackTrace();
            return false;
        }
    }

    public static Object a(String str, String str2) {
        return a(new File(str + File.separator + str2));
    }

    public static Object a(File file) {
        Object object = null;
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return object;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return object;
        } catch (IOException e3) {
            e3.printStackTrace();
            return object;
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
            return object;
        }
    }
}
