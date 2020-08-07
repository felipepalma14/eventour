# Teste desenvolvedor android

* Componente do Jetpack utilizados:
    - Coroutines
    - LiveData
    - DataBinding
    - Room
    - Navigation
    - ViewModel

* Arquitetura
    - A arquitetura do projeto está em MVVM

* Conexão de Dados
    - Para conexão de dados foi utilizado a lib Retrofit
* Processamento de Imagem
    - A lib Glide faz todo o processamento das imagens utilizadas no app
* UI
    - Para a criação do layout foram utilizados ConstrainLayout, Navigation graph, CardView e RecyclerView
* Injeção de Dependência
    - Dagger 2.24
* Tests
    - Mockito
* Recursos adicionados
    - Offline fist - Este recurso permite o usuario utilizar o app mesmo sem conexão, quando o app
está conectado à internet os dados baixados são persistidos no banco de dados com auxilio da lib Room
e são mostrados para o usuario utilizado Single Source of Truth