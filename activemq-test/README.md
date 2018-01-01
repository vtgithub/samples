# hdfs-sample
### Dependencies
* jetty-server-8.1.8
* hadoop 2.8.1

### deployment
* After creating jar file run following linux command :
  - `zip -d you-jar-file.jar 'META-INF/*.SF' 'META-INF/*.RSA' 'META-INF/*SF'`
### usage
* add header `fileType` to your request that contains the file with `POST` method. you will get `fileName` in response.
* to getting uploaded files just call `localhost:9595?fileName=`
* until now the code supports pdf, audio files, mp4, images
 
