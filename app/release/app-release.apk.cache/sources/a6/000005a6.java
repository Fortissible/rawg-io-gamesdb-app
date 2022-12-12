package d1;

import a6.i;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: e  reason: collision with root package name */
    public static final HashMap f3036e = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final File f3037a;

    /* renamed from: b  reason: collision with root package name */
    public final Lock f3038b;
    public final boolean c;

    /* renamed from: d  reason: collision with root package name */
    public FileChannel f3039d;

    public a(String str, File file, boolean z7) {
        Lock lock;
        File file2 = new File(file, i.e(str, ".lck"));
        this.f3037a = file2;
        String absolutePath = file2.getAbsolutePath();
        HashMap hashMap = f3036e;
        synchronized (hashMap) {
            lock = (Lock) hashMap.get(absolutePath);
            if (lock == null) {
                lock = new ReentrantLock();
                hashMap.put(absolutePath, lock);
            }
        }
        this.f3038b = lock;
        this.c = z7;
    }

    public final void a() {
        FileChannel fileChannel = this.f3039d;
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
        this.f3038b.unlock();
    }
}