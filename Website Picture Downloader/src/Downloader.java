import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Downloader {

        public static void download(String downloadUrl) throws IOException {
            URL url = new URL(downloadUrl);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5000);
            InputStream is = con.getInputStream();
            int start = downloadUrl.lastIndexOf("/");
            String filename = new String(downloadUrl.substring(start, downloadUrl.length()));
            byte[] bs = new byte[1024];
            File f = new File("F:\\JavaTest");
            if(!f.exists()) {
                f.mkdirs();
            }

            FileOutputStream os = new FileOutputStream(f.getPath() + "\\" + filename);

            int len;
            while((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

            os.close();
            is.close();
        }

        public static void main(String[] args) {
            try {
                URL e = new URL("http://blog.jobbole.com/");
                BufferedReader in = new BufferedReader(new InputStreamReader(e.openStream()));

                String s;
                while((s = in.readLine()) != null) {
                    String ps = new String("<img.*src=\"(.*?)\".*>");
                    Pattern pat = Pattern.compile(ps);
                    Matcher mat = pat.matcher(s);
                    boolean isMatched = mat.find();
                    if(isMatched) {
                        System.out.println(mat.group(1));
                        download(mat.group(1));
                        isMatched = false;
                    }
                }

                in.close();
            } catch (MalformedURLException var8) {
                var8.printStackTrace();
            } catch (IOException var9) {
                var9.printStackTrace();
            }

        }
    }
