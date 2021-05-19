# Lab06 - O Mundo de Wumpus
Estrutura de pastas:

~~~
├── README.md  <- arquivo apresentando a tarefa
│
├── data       <- dados usados pelo jogo (arquivos CSV)
│
└── src <- pasta de arquivos fonte Java (.java)
|   │
|   └── mc322 <- raiz do seu pacote
|       │
|       └── lab06 <- arquivos fonte .java
│
└── bin <- pasta de arquivos binários Java (.class)
   │
   └── mc322 <- raiz do seu pacote
       │
       └── lab06 <- arquivos binários .class

~~~

## Arquivos Java do Jogo

Para acionar o jogo, é necessário executar os arquivos binários a partir do root do pacote, passando como parâmetro na linha de comando o caminho do arquivo de entrada .csv.

Um exemplo de uso é (a partir desse diretório): `java -cp bin/ mc322.lab06.AppMundoWumpus data/caverna1.csv`

[Código fonte](src/mc322/lab06)

## Destaques de Arquitetura

### `Notificações`

~~~java
public static void addNotificacao(String s) {
	notificacoes[indice_notif] = s;
	indice_notif += 1;
}
	
private void lerNotificacoes() {
	for(int i = 0; i < indice_notif; i++) 
		println(notificacoes[i]);
		
	indice_notif = 0;
}
~~~

Com essas funções estáticas da classe `Controle`, nós podemos reunir em apenas um local a lógica por trás do display de informações (notificações) do jogo para o usuário. O poder desse design vem da facilidade de alterar o método de display dessas informações (por exemplo, caso implementassemos uma UI gráfica para o jogo), sem a necessidade de alterar outras partes do código. Assim, a parte responsável pelo display das informações fica separada da lógica do jogo em sí.
