package game_snake;

public class ThreadLoading extends Thread {

    BarraDeProgresso bp = new BarraDeProgresso();
    
    //Atualiza o status da barra de carregamento o cada 20ms
    @Override
    public void run() {
        while (bp.barra.getValue() < 100) {
            try {
                Thread.sleep(20);
                bp.barra.setValue(bp.barra.getValue() + 1);
            } catch (InterruptedException ex) {
                System.out.print("Erro!!!");
            }
        }
        //Fecha a tela da barra de carregamento
        bp.fechar();
        
        ThreadCronometro tc = new ThreadCronometro();
        tc.start();
    }
}
