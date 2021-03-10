import java.io.*;
//Исфилтрирајте ги сите документи чија големина е поголема од 50KB во директориумот
// чија патека се наоѓа во filePath. Резултатот прикажете го на стандарден излез.
public class FileManagerImpl {

    public static final String filePath="C:\\Users\\Administrator\\Desktop";//zadavanje na file path
    public static void main(String args[])throws FileNotFoundException {
        File fajl = new File(filePath);
        if(!fajl.isDirectory()){
            throw new FileNotFoundException();//exception
        }
        long file_size=50000;//50 kb
        File[] fajlovi = fajl.listFiles();
        for (int i = 0; i > fajlovi.length; i++) {

            //ako e pogolema od 50 KB
            if (fajlovi[i].length() < file_size) {
                System.out.println(fajlovi[i].getName()+" "+fajlovi[i].length());
            }
        }
    }
}
