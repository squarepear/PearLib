package com.reygames.pearlib.api;

import java.io.*;

public class FileSerialization {
    public static boolean serialize(Object object, String path, String fileName)
    {
        File file = new File(path, fileName);
        if (!file.exists()) {
            new File(path).mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            FileOutputStream fos = new FileOutputStream(path + File.separator + fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object deserialize(String path, String fileName) {
        File file = new File(path, fileName);
        if (!file.exists()) {
            new File(path).mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileInputStream fis = new FileInputStream(path + File.separator + fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object result = ois.readObject();
                ois.close();
                return result;
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
