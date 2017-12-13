# java-servlet
simple java servlet for beginners
### test uris
* /static-test: returns static-page.html by only web.xml config
* /test : return test.jsp file using `ServletTest` class  that utilies `RequestDispatcher` class. This page contains 2 section `header` and `footer`. The file contains alson `El` tags and a simple conditional `JSTL` tag.
* /forward-test : by `?forward` query parameter if `true` forward to static-test else include include-page.html
