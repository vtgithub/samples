package ir.ord.application.accessories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DocHelper{
        private String fileName;

        public DocHelper(String fileName) {
            this.fileName = fileName;
        }

        public String getTableFomEnum() throws IOException {
            String fileContent = getFileContent(this.fileName);
            String mainContent = fileContent.substring(fileContent.indexOf("{"), fileContent.indexOf(";"));
            return mainContent;
        }

        private String getFileContent(String fileName) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine())!= null){
                sb.append(line);
            }
            return sb.toString();
        }


    }