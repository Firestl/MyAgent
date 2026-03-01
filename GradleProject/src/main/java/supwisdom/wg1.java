package supwisdom;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import supwisdom.lh1;

/* JADX INFO: compiled from: ContactsPhotoRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class wg1 extends lh1 {
    public static final UriMatcher b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9623a;

    /* JADX INFO: compiled from: ContactsPhotoRequestHandler.java */
    @TargetApi(14)
    public static class a {
        public static InputStream a(ContentResolver contentResolver, Uri uri) {
            return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        b = uriMatcher;
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        b.addURI("com.android.contacts", "contacts/lookup/*", 1);
        b.addURI("com.android.contacts", "contacts/#/photo", 2);
        b.addURI("com.android.contacts", "contacts/#", 3);
        b.addURI("com.android.contacts", "display_photo/#", 4);
    }

    public wg1(Context context) {
        this.f9623a = context;
    }

    @Override // supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        Uri uri = jh1Var.d;
        return "content".equals(uri.getScheme()) && ContactsContract.Contacts.CONTENT_URI.getHost().equals(uri.getHost()) && b.match(jh1Var.d) != -1;
    }

    public final InputStream c(jh1 jh1Var) throws IOException {
        ContentResolver contentResolver = this.f9623a.getContentResolver();
        Uri uriLookupContact = jh1Var.d;
        int iMatch = b.match(uriLookupContact);
        if (iMatch != 1) {
            if (iMatch != 2) {
                if (iMatch != 3) {
                    if (iMatch != 4) {
                        throw new IllegalStateException("Invalid uri: " + uriLookupContact);
                    }
                }
            }
            return contentResolver.openInputStream(uriLookupContact);
        }
        uriLookupContact = ContactsContract.Contacts.lookupContact(contentResolver, uriLookupContact);
        if (uriLookupContact == null) {
            return null;
        }
        return Build.VERSION.SDK_INT < 14 ? ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uriLookupContact) : a.a(contentResolver, uriLookupContact);
    }

    @Override // supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        InputStream inputStreamC = c(jh1Var);
        if (inputStreamC != null) {
            return new lh1.a(inputStreamC, Picasso.LoadedFrom.DISK);
        }
        return null;
    }
}
