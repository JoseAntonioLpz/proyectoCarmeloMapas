package com.izv.dam.newquip.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.izv.dam.newquip.pojo.Nota;

/**
 * Created by josea on 19/11/2016.
 */

public class BaseDatosDb4o {
    public static ObjectContainer CrearBaseDeDatos(String nom){
        ObjectContainer bd= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nom+".db4o");
        return bd;
    }

    public static int añadirNota(Nota n,ObjectContainer bd){
        try{
            bd.store(n);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static int visualizarNotas(ObjectContainer bd){//Por consola
        int i=0;
        try{
            ObjectSet os=bd.queryByExample(new Nota(0,null,null));
            while(os.hasNext()){
                System.out.println(os.next().toString());
                i++;
            }
            return i;
        }catch(Exception e){
            e.printStackTrace();
        }
        return i;
    }
    public static int borrarAlumnos(Nota n,ObjectContainer bd){
        try{
            bd.delete(n);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public static Nota recuperarNota(Nota n,ObjectContainer bd){
        ObjectSet os=bd.queryByExample(n);
        return (Nota) os.next();
    }
}
