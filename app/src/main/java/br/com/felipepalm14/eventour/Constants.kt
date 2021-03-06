package br.com.felipepalm14.eventour

const val BASE_URL = "http://5b840ba5db24a100142dcd8c.mockapi.io/api/"
const val EVENTS   = "events"
const val EVENT   = "${EVENTS}/{id}"
const val CHECKIN  = "checkin"

const val JSON = "{\n" +
        "  \"date\": 1534784400000,\n" +
        "  \"description\": \"O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\\n\\nNa ocasião, teremos bottons, bloquinhos e camisetas!\\n\\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \\n\\nAceitaremos todos os tipos de doação:\\n- guias e coleiras em bom estado\\n- ração (as que mais precisamos no momento são sênior e filhote)\\n- roupinhas \\n- cobertas \\n- remédios dentro do prazo de validade\",\n" +
        "  \"image\": \"http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png\",\n" +
        "  \"longitude\": -51.2146267,\n" +
        "  \"latitude\": -30.0392981,\n" +
        "  \"price\": 29.99,\n" +
        "  \"title\": \"Feira de adoção de animais na Redenção\",\n" +
        "  \"id\": \"1\",\n" +
        "  \"cupons\": [\n" +
        "    {\n" +
        "      \"id\": \"1\",\n" +
        "      \"eventId\": \"1\",\n" +
        "      \"discount\": 62\n" +
        "    }\n" +
        "  ]\n" +
        "}"
const val JSON_EVENTS = "[{\"people\":[{\"id\":\"1\",\"eventId\":\"1\",\"name\":\"name 1\",\"picture\":\"picture 1\"}],\"date\":1534784400000,\"description\":\"O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\\n\\nNa ocasião, teremos bottons, bloquinhos e camisetas!\\n\\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \\n\\nAceitaremos todos os tipos de doação:\\n- guias e coleiras em bom estado\\n- ração (as que mais precisamos no momento são sênior e filhote)\\n- roupinhas \\n- cobertas \\n- remédios dentro do prazo de validade\",\"image\":\"http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png\",\"longitude\":-51.2146267,\"latitude\":-30.0392981,\"price\":29.99,\"title\":\"Feira de adoção de animais na Redenção\",\"id\":\"1\",\"cupons\":[{\"id\":\"1\",\"eventId\":\"1\",\"discount\":62}]},{\"people\":[{\"id\":\"2\",\"eventId\":\"2\",\"name\":\"name 2\",\"picture\":\"picture 2\"}],\"date\":1534784400000,\"description\":\"Vamos ajudar !!\\n\\nSe você tem na sua casa roupas que estão em bom estado de uso e não sabemos que fazer, coloque aqui na nossa página sua cidade e sua doação, concerteza poderá ajudar outras pessoas que estão passando por problemas econômicos no momento!!\\n\\nAjudar não faz mal a ninguém!!!\\n\",\"image\":\"http://fm103.com.br/wp-content/uploads/2017/07/campanha-do-agasalho-balneario-camboriu-2016.jpg\",\"longitude\":-51.2148497,\"latitude\":-30.037878,\"price\":59.99,\"title\":\"Doação de roupas\",\"id\":\"2\",\"cupons\":[{\"id\":\"2\",\"eventId\":\"2\",\"discount\":30}]},{\"people\":[{\"id\":\"3\",\"eventId\":\"3\",\"name\":\"name 3\",\"picture\":\"picture 3\"}],\"date\":1534784400000,\"description\":\"Atenção! Para nosso brique ser o mais organizado possível, leia as regras e cumpra-as:\\n* Ao publicar seus livros, evite criar álbuns (não há necessidade de remetê-los a nenhum álbum);\\n* A publicação deverá conter o valor desejado;\\n* É preferível publicar uma foto do livro em questão a fim de mostrar o estado em que se encontra;\\n* Respeite a ordem da fila;\\n* Horário e local de encontro devem ser combinados inbox;\\n* Caso não possa comparecer, avise seu comprador/vendedor previamente;\\n* Caso seu comprador desista, comente o post com \\\"disponível\\\";\\n* Não se esqueça de apagar a publicação se o livro já foi vendido, ou ao menos comente \\\"vendido\\\" para que as administradoras possam apagá-la;\\n* Evite discussões e respeite o próximo;\\n\",\"image\":\"http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg\",\"longitude\":-51.2148497,\"latitude\":-30.037878,\"price\":19.99,\"title\":\"Feira de Troca de Livros\",\"id\":\"3\",\"cupons\":[{\"id\":\"3\",\"eventId\":\"3\",\"discount\":17}]}]"