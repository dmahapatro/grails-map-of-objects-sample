import com.example.Author
import com.example.Book

class BootStrap {

    def init = { servletContext ->
        def progGrails = new Book(name: "Programming Grails")
        def gina = new Book(name: "Grails In Action")
        def grina = new Book(name: "Groovy In Action")

        progGrails.authors = [burt: new Author(name: "Burt Beckwith")]
        gina.authors = [glen: new Author(name: "Glen Smith"), peter: new Author(name: "Peter Ledbrook")]
        grina.authors = [guillaume: new Author(name: "Guillaume Laforge")]

        [progGrails, gina, grina]*.save(flush: true)
    }
    def destroy = {
    }
}
