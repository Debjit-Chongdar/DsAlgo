package design_pattern.behavioural;

abstract class Parser{
    public void parse(String file){
        this.open(file);
        this.parseContent(file);
        this.close(file);
    }
    private void open(String file){
        System.out.println("open file "+file);
    }
    protected abstract void parseContent(String file);
    private void close(String file){
        System.out.println("close file "+file);
    }
}
class CsVPerser extends Parser{
    @Override
    protected void parseContent(String file) {
        System.out.println("Parse csv content");
    }
}
class JsonParser extends Parser{
    @Override
    protected void parseContent(String file) {
        System.out.println("Parse JSON content");
    }
}

public class Template {
    public static void main(String[] args) {
        Parser csv = new CsVPerser();
        Parser json = new JsonParser();
        csv.parse("abc.csv");
        json.parse("xyz.json");
    }
}
