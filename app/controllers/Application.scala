package controllers

import java.io.{FileWriter, BufferedWriter, File}

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.api._
import play.api.mvc._
import views.html

import scala.util.{Failure, Success}

class Application extends Controller {

  def index = Action {
 var url = "http://mcapitalp.com/"
    var fileName = "mcapitalp.txt"
    var doc : Document = Jsoup.connect(url).timeout(0).get();
    var links : Elements = doc.select("a[href]");
    //var media : Elements = doc.select("[src]");
    //var imports : Elements= doc.select("link[href]");


    //printf("\nMedia: (%d)" , media.size())
    //var src : Element= null

   /* while(media.iterator().hasNext){
      src = media.iterator().next()

      if (src.tagName().equals("img"))
        printf(" * %s: <%s> %sx%s (%s)",
          src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
          trim(src.attr("alt"), 20));
      else
        printf(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
    }*/


   /* printf("\nImports: (%d)", imports.size());
    while(imports.iterator().hasNext){
      var link = imports.iterator().next();
      printf(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
    }*/

    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    var Investmentslinks : Elements=null
    for(i <- 0 to links.size() -1){
      var link = links.get(i)

     if(link.text() == "Investments" || link.text() == "Portfolio companies"){
       var Investmentsurl =link.attr("abs:href")
        var Investmentsdoc : Document = Jsoup.connect(Investmentsurl).timeout(0).get();
       Investmentslinks = Investmentsdoc.select("a[href]");

     }
    }
    if (Investmentslinks != null)
      links.addAll(Investmentslinks)


    bw.write("\nLinks: " + links.size());
    for(i <- 0 to links.size() -1){
      var link = links.get(i)

      bw.write("\n" + i +  "   "+ link.text())
      bw.write(link.attr("abs:href"))
    }

    bw.close()
    Ok(html.index("asd") )
  }



  private def trim(s : String, width : Integer) : String = {
    if (s.length() > width)
      return s.substring(0, width-1) + ".";
    else
      return s;
  }

}
