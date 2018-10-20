/** функциональность класса OrcReader открывает все файлы orc в текущей директории,
 * читает данные из файлов и переносит в тектовые файлы, создаваемые в этой же директории
 * автор Viatcheslav Losev
 */


import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.Reader;
import org.apache.hadoop.hive.ql.io.orc.RecordReader;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class OrcReader {

    public static void main(String[] argv)
    {
        try {
            System.setProperty("hadoop.home.dir", System.getProperty("user.dir"));
            Logger.getRootLogger().setLevel(Level.OFF);
            Configuration conf = new Configuration();
            List<File> files = (List<File>) FileUtils.listFiles(new File(System.getProperty("user.dir")), null, true);
            for (File file : files) {
                String s = file.getCanonicalPath();
                String subs = s.substring(s.lastIndexOf('.') + 1);
                if(subs.equals("orc")){
                    //System.out.println("s -- " + s);
                    //System.out.println("subs -- " + subs);
                    orcIterator(conf, s);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void orcIterator(Configuration conf, String nameOrcMain){
        try {

            Reader reader = OrcFile.createReader(FileSystem.get(conf), new Path(nameOrcMain));
            StructObjectInspector inspector = (StructObjectInspector) reader.getObjectInspector();
            //System.out.println(reader.getMetadata() + "   УРА!!!");
            String filepath = nameOrcMain + ".txt";
            FileWriter writerLog = new FileWriter(filepath);
            RecordReader records = reader.rows();
            Object row = null;
            //
            List fields = inspector.getAllStructFieldRefs();
            for(int i = 0; i < fields.size(); ++i) {
                System.out.print(((StructField)fields.get(i)).getFieldObjectInspector().getTypeName() + '\t');
            }

            while(records.hasNext())
            {
                row = records.next(row);
                List value_lst = inspector.getStructFieldsDataAsList(row);
                StringBuilder builder = new StringBuilder();
                //
                for(Object field : value_lst) {
                    if(field != null)
                        builder.append(field.toString());
                    builder.append("  ");

                }
                //
                System.out.println(builder.toString());
                builder.append(System.lineSeparator());
                writerLog.write(builder.toString());

            }
            writerLog.flush();
            writerLog.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
