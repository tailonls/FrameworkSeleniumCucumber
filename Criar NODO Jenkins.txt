1) Configurações:

	Configurar segurança global ->	TCP port for inbound agents	-> Mudar para 'Randon'
	Configurar o sistema -> URL do Jenkins -> mudar para o ip do seu pc master: http://192.168.0.22:8080/ (ipconfig no cmd)

2) Criar Nodo:

	Gerenciar Jenkins -> Gerenciar nós -> Novo no:
	
		Nome do nó: NODO_ALGUMA_COISA
		Permanent agent: sim
		Numero de executores: quantos Jobs podem rodar nesse no ao mesmo tempo
		Diretório root remoto: pode ser o local onde foi instalado o jenkins (ex. C:/Program Files (x86)/Jenkins)
		Labels: ex. WINDOWNS_SLAVE, LINUX_SLAVE,
		Use: "Use this node as much as possible"
		Método de lançamento: "Launch agent via execution of command on the master"
		Disponibilidade: "Keep this agent online as much as possible"
		
	Ao salvar, na tela do jenkins Nodo aparecerá como offline

	Baixar o arquivo "slave-agent.jnlp"
	Abrir cmd no diretorio onde esta esse arquivo
	
	Ativar slave: javaws .\slave-agent.jnlp -O (necessita ter o java instalado)
	Verificar se esta de pé: get-service jenkinsslave-C_Jenkins
	
	Ou...
	
	java -jar agent.jar -jnlpUrl http://localhost:8080/computer/TESTE/slave-agent.jnlp -secret eb6431b81af02c0ac3d7c50db29e496e6e68b7e826620e68a441f4ec815a2510 -workDir "C:/Program Files (x86)/Jenkins
	
	- Na tela do jenkins Nodo aparecerá como online
	