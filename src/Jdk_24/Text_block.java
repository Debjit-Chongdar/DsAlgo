package Jdk_24;

public class Text_block {

    String old_way =    "{" +
                            "\"name\": \"Alis\","+
                            "\"address\": \"24-state street\","+
                            "\"age\": 30"+
                        "}";

    String new_way = """
            {
                "name": "Alis",
                "address": "24-state street",
                "age": 30
            }
            """;
}
