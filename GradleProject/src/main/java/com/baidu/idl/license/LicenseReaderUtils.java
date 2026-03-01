package com.baidu.idl.license;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class LicenseReaderUtils {
    public static final String TAG = "License-SDK";

    public static InputStream get_local_license_file_inputstream(Context context, String str) {
        if (context == null) {
            return null;
        }
        FileInputStream fileInputStream = read_license_from_data(context, str);
        Log.e("License-SDK", "read_license_from_data");
        if (fileInputStream != null) {
            return fileInputStream;
        }
        Log.e("License-SDK", "read_license_from_asset");
        return read_license_from_asset(context, str);
    }

    public static ArrayList<String> read_license_content(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> arrayList = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return arrayList;
            }
            arrayList.add(line);
        }
    }

    public static InputStream read_license_from_asset(Context context, String str) {
        if (context == null) {
            Log.e("License-SDK", "read_license_from_asset context is null");
            return null;
        }
        try {
            return context.getAssets().open(str);
        } catch (IOException e2) {
            Log.e("License-SDK", "read_license_from_asset IOException");
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            Log.e("License-SDK", "read_license_from_asset Exception " + e3.getMessage());
            e3.printStackTrace();
            return null;
        }
    }

    public static FileInputStream read_license_from_data(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            File dir = context.getDir(str, 0);
            if (dir != null && dir.exists() && dir.isFile()) {
                if (dir.length() != 0) {
                    return new FileInputStream(dir);
                }
                Log.e("License-SDK", "read_license_from_data file is empty");
                return null;
            }
            Log.e("License-SDK", "read_license_from_data file not found");
            return null;
        } catch (FileNotFoundException e2) {
            Log.e("License-SDK", "read_license_from_data FileNotFoundException");
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            Log.e("License-SDK", "read_license_from_data Exception " + e3.getMessage());
            e3.printStackTrace();
            return null;
        }
    }

    public static boolean write_license_content(Context context, String str, ArrayList<String> arrayList) throws Throwable {
        Log.e("License-SDK", "write_license_content");
        boolean z = false;
        if (arrayList == null || arrayList.size() == 0 || context == null) {
            return false;
        }
        File dir = context.getDir(str, 0);
        if (dir != null && dir.exists()) {
            dir.delete();
        }
        if (dir != null && !dir.exists()) {
            try {
                dir.createNewFile();
            } catch (IOException e2) {
                Log.e("License-SDK", "write_license_content IOException");
                e2.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(dir);
                    try {
                        Iterator<String> it = arrayList.iterator();
                        while (it.hasNext()) {
                            fileOutputStream2.write(it.next().getBytes());
                            fileOutputStream2.write(10);
                        }
                        fileOutputStream2.close();
                        z = true;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        Log.e("License-SDK", "write_license_content FileNotFoundException");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return z;
                    } catch (IOException e4) {
                        e = e4;
                        fileOutputStream = fileOutputStream2;
                        Log.e("License-SDK", "write_license_content IOException");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                } catch (IOException e7) {
                    e = e7;
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
