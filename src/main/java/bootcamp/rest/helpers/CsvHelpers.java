package bootcamp.rest.helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import bootcamp.rest.models.entities.Article;
import bootcamp.rest.models.entities.Author;
import bootcamp.rest.models.entities.Category;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

public class CsvHelpers {
    
    public static final String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }

        return true;
    }

    public static List<Article> csvToArticle(InputStream inputStream) throws java.io.IOException{
        try{
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Article> article = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for(CSVRecord csvRecord: csvRecords){
                Article articleRecord = new Article();
                Author authorRecord = new Author();
                Category categoryRecord = new Category();

                articleRecord.setId(Long.parseLong(csvRecord.get("article_id")));
                articleRecord.setDate(csvRecord.get("date"));
                articleRecord.setTitle(csvRecord.get("title"));
                articleRecord.setDescription(csvRecord.get("description"));

                authorRecord.setId(Long.parseLong(csvRecord.get("author_id"))); //Must Available

                articleRecord.setAuthor(authorRecord);

                categoryRecord.setId(Long.parseLong(csvRecord.get("category_id"))); //Must Available

                articleRecord.setCategory(categoryRecord);

                article.add(articleRecord);
            }
            parser.close();
            return article;

        }catch(IOException ex){
            throw new RuntimeException("Fail to parse CSV file: " + ex.getMessage());
        }
    }
}
