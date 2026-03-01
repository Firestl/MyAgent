package com.vivo.push.c;

/* JADX INFO: compiled from: PushClientTaskFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class af {
    public static com.vivo.push.l a(com.vivo.push.o oVar) {
        com.vivo.push.l aeVar;
        int iB = oVar.b();
        if (iB == 20) {
            aeVar = new ae(oVar);
        } else if (iB == 100) {
            aeVar = new b(oVar);
        } else if (iB != 101) {
            switch (iB) {
                case 0:
                    break;
                case 1:
                    aeVar = new z(oVar);
                    break;
                case 2:
                    aeVar = new h(oVar);
                    break;
                case 3:
                    aeVar = new p(oVar);
                    break;
                case 4:
                    aeVar = new r(oVar);
                    break;
                case 5:
                    aeVar = new u(oVar);
                    break;
                case 6:
                    aeVar = new w(oVar);
                    break;
                case 7:
                    aeVar = new n(oVar);
                    break;
                case 8:
                    aeVar = new l(oVar);
                    break;
                case 9:
                    aeVar = new g(oVar);
                    break;
                case 10:
                    aeVar = new d(oVar);
                    break;
                case 11:
                    aeVar = new ac(oVar);
                    break;
                case 12:
                    aeVar = new f(oVar);
                    break;
                default:
                    switch (iB) {
                        case 2000:
                        case 2001:
                        case 2002:
                        case 2003:
                        case 2004:
                        case 2005:
                        case 2008:
                        case 2009:
                        case 2010:
                        case 2011:
                        case 2012:
                        case 2013:
                        case 2014:
                        case 2015:
                            break;
                        case 2006:
                            aeVar = new a(oVar);
                            break;
                        case 2007:
                            aeVar = new ah(oVar);
                            break;
                        default:
                            return null;
                    }
                    break;
            }
            aeVar = new ag(oVar);
        } else {
            aeVar = new c(oVar);
        }
        return aeVar;
    }

    public static y b(com.vivo.push.o oVar) {
        y aeVar;
        int iB = oVar.b();
        if (iB == 20) {
            aeVar = new ae(oVar);
        } else if (iB != 2016) {
            switch (iB) {
                case 1:
                    aeVar = new z(oVar);
                    break;
                case 2:
                    aeVar = new h(oVar);
                    break;
                case 3:
                    aeVar = new p(oVar);
                    break;
                case 4:
                    aeVar = new r(oVar);
                    break;
                case 5:
                    aeVar = new u(oVar);
                    break;
                case 6:
                    aeVar = new w(oVar);
                    break;
                case 7:
                    aeVar = new n(oVar);
                    break;
                case 8:
                    aeVar = new l(oVar);
                    break;
                case 9:
                    aeVar = new g(oVar);
                    break;
                case 10:
                    aeVar = new d(oVar);
                    break;
                case 11:
                    aeVar = new ac(oVar);
                    break;
                default:
                    return null;
            }
        } else {
            aeVar = new k(oVar);
        }
        return aeVar;
    }
}
