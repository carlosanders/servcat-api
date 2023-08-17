# SERVCAT API

## Propósito:

O Webservice...

## Informações Técnicas

- **JAVA 17+**
- **Maven 3.9+**
- **Arquitetura: REST** - (Representational State Transfer)
- **Formato de Serialização de Dados: JSON** (JavaScript Object Notation)

```bash
# Clonando o projeto do site
git clone git@github.com:carlosanders/servcat-api.git

# ou se desejar baixar para outra pasta
git clone git@github.com:carlosanders/servcat-api.git webservice-servcat

# se não possuir a chave SSH na conta tem baixar via HTTPS
# será pedido usuario/senha da sua conta do http://www.gitlab.com
git clone  https://github.com/carlosanders/servcat-api.git
```

##### Configurar o Git:

```bash
# configurar nome e email no nível global do sistema para identificar no commit
git config --global user.name "Seu Nome"
git config --global user.email "email@provedor.com"

# Para conferir execute
git config user.name
git config user.email

# Para listar todas as configurações
git config --list
```

##### Criando um chave SSH Para criar uma chave privada/publica no git:

```bash
ssh-keygen -t rsa -C "seuemails@provedor.com"
# Para listar a chave pública
cat ~/.ssh/id_rsa.pub
# ou se preferir pelo editor de texto padrão ou cacadastrado
subl ~/.ssh/id_rsa.pub
```

##### Iniciando Push an existing Git no projeto:

```bash
# verficar os branches trazidos do repositório com os commits
git remote rename origin old-origin
git remote add origin git@github.com:carlosanders/servcat-api.git
git remote add origin https://github.com/carlosanders/servcat-api.git
git push -u origin --all
git push -u origin --tags
```

### Executando a Aplicação:

```bash
# Dentro da raiz do projeto, como por exemplo em:
cd /d ...\web_services\servcat-api
# execute o comando:
mvn spring-boot:run
# E para cancelar a execução use CTRL+C

# caso desejar rodar pela IDE
# Isso pode ser feito usando Run da IDE em:
servcat-api\src\main\java\mb\dabm\servcatapi\ServcatApiApplication.java
```

#### Gerando o WAR:

Certifique-se de o arquivo `pom.xml` está configurado com a tag `packaging` para o valor: `war`, se estiver com o valor `jar` o arquivo gerado será um arquivo compactado para ser executado.

```bash
# Para gerar o war, pelo terminal vá na na raiz do projeto, como por exemplo em:
../web_services/servcat-api
# E digite os camandos:
mvn clean package
mvn package
mvn package -Dmaven.test.skip
#Se tudo correr certo deve aparecer no final uma mensagem  como esta:
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 21.051 s
[INFO] Finished at: 2019-09-19T16:38:43-03:00
[INFO] ------------------------------------------------------------------------
# se gerou o arquivo .jar para executar será o comando:
java -jar target/nome-arquivo-java.jar
# ou caso queira alterar algum parametro do resource
# ex.: alterar usuario/senha banco
java -jar nome-arquivo-java.jar --spring.datasource.username=root --spring.datasource.password=root
# para alterar ambiente de execucao:
java -jar -Dspring.profiles.active=dev1 nome-arquivo-java.jar
```

#### caso desejar altera o nome do arquivo 'war' ou 'jar' na complicação

Alterar a tag `finalName` dentro do arquivo `pom.xml`

```xml
<!-- exemplo: -->
<build>
    <finalName>servcat-api</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
