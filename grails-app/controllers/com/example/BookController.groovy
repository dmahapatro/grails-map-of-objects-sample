package com.example

class BookController {

    def index() {

        assert Book.count() == 3

        def queryList = ['burt', 'glen', 'peter', 'guillaume']

        //HQL with Key
        def hqlQuery = """
                        select distinct b from Book as b \
                        inner join b.authors as auth \
                        where index(auth) in :authKeys \
                        order by b.name
                       """
        def hqlResult = Book.executeQuery(hqlQuery, [authKeys: queryList])

        assert hqlResult*.name == ['Grails In Action', 'Groovy In Action', 'Programming Grails']

        //HQL with Key
        def hqlKeyValQuery = """
                                select b from Book as b \
                                inner join b.authors as auth \
                                where index(auth) in :authKeys \
                                and auth.name = 'Peter Ledbrook' \
                                order by b.name
                             """
        def hqlResultKeyValue = Book.executeQuery(hqlKeyValQuery, [authKeys: queryList])

        assert hqlResultKeyValue*.name == ["Grails In Action"]

        //findAll
        def findAllQuery = """
                            from Book as b inner join fetch b.authors as auth \
                            where index(auth) in :authKeys order by b.name
                           """
        def findAllResult = Book.findAll(findAllQuery, [authKeys: queryList])

        assert findAllResult*.name == ['Grails In Action', 'Grails In Action', 'Groovy In Action', 'Programming Grails']

        render 'ok'
    }
}
