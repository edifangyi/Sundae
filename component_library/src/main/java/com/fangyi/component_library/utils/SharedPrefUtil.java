package com.fangyi.component_library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/7/8
 * 说    明：
 * ================================================
 */
public class SharedPrefUtil {
    private SharedPreferences preferences;
    private Editor editor;
    private Context mContext;

    public SharedPrefUtil(Context context) {
        this.mContext = context;
        this.preferences = this.mContext.getSharedPreferences(this.mContext.getPackageName(), 0);
        this.editor = this.preferences.edit();
    }

    public SharedPrefUtil(Context context, String name) {
        this.mContext = context;
        this.preferences = this.mContext.getSharedPreferences(name, 0);
        this.editor = this.preferences.edit();
    }

    public void putValue(String key, Object value) {
        try {
            if (value instanceof Integer) {
                this.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                this.putBool(key, (Boolean) value);
            } else if (value instanceof Float) {
                this.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                this.putLong(key, (Long) value);
            } else {
                this.putString(key, value.toString());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void putString(String key, String value) {
        this.editor.putString(key, value);
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public String getString(String key) {
        return this.getString(key, "");
    }

    public String getString(String key, String defaultVal) {
        return this.preferences.getString(key, defaultVal);
    }

    public void putInt(String key, int value) {
        this.editor.putInt(key, value);
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defaultVal) {
        return this.preferences.getInt(key, defaultVal);
    }

    public void putBool(String key, boolean value) {
        this.editor.putBoolean(key, value);
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public boolean getBool(String key) {
        return this.getBool(key, false);
    }

    public boolean getBool(String key, boolean defaultVal) {
        return this.preferences.getBoolean(key, defaultVal);
    }

    public void putFloat(String key, float value) {
        this.editor.putFloat(key, value);
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultVal) {
        return this.preferences.getFloat(key, defaultVal);
    }

    public void putLong(String key, long value) {
        this.editor.putLong(key, value);
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public long getLong(String key, long defaultVal) {
        return this.preferences.getLong(key, defaultVal);
    }

    public <T extends Serializable> void putObject(String key, T obj) {
        this.put(key, obj);
    }

    public <T extends Serializable> T getObject(String key) {
        try {
            return (T) this.get(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public void putList(String key, List<? extends Serializable> list) {
        this.put(key, list);
    }

    public <E extends Serializable> List<E> getList(String key) {
        try {
            return (List) this.get(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public <K extends Serializable, V extends Serializable> void putMap(String key, Map<K, V> map) {
        this.put(key, map);
    }

    public <K extends Serializable, V extends Serializable> Map<K, V> getMap(String key) {
        try {
            return (Map) this.get(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    private void put(String key, Object obj) {
        try {
            if (obj == null) {
                return;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            String objectStr = new String(Base64.encode(baos.toByteArray(), 0));
            baos.close();
            oos.close();
            this.putString(key, objectStr);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    private Object get(String key) {
        try {
            String wordBase64 = this.getString(key);
            if (TextUtils.isEmpty(wordBase64)) {
                return null;
            } else {
                byte[] objBytes = Base64.decode(wordBase64.getBytes(), 0);
                ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object obj = ois.readObject();
                bais.close();
                ois.close();
                return obj;
            }
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public void remove(String key) {
        if (this.contains(key)) {
            this.editor.remove(key);
            SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
        }

    }

    public void clear() {
        this.editor.clear();
        SharedPrefUtil.SharedPreferencesCompat.apply(this.editor);
    }

    public boolean contains(String key) {
        return this.preferences.contains(key);
    }

    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        private static Method findApplyMethod() {
            try {
                Class clz = Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException var1) {
                return null;
            }
        }

        public static void apply(Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException var2) {
                ;
            } catch (IllegalAccessException var3) {
                ;
            } catch (InvocationTargetException var4) {
                ;
            }

            editor.commit();
        }
    }
}
