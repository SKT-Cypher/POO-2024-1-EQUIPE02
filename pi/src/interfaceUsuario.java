import javax.swing.JOptionPane;

public class interfaceUsuario {

    public void exibirInterface(Usuario usuario) {
        boolean usando = true;
        while (usando) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "===== Menu =====      Usuário: "+usuario.getNome()+"\n\n1. Comprar Ingresso\n2. Emitir ingresso eletrônico\n3. Cancelar ingresso\n0. Voltar"));
            switch (opcao) {
                case 1:
                    comprarIngresso();
                    break;
                case 2:
                    emitirIngressoEletronico();
                    break;
                case 3:
                    
                    break;
                case 0:
                    usando = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.");
                    break;
            }
        }
    }

    private void comprarIngresso() {
    		/*if (eventoParametro != null) {
            String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");
            boolean ingressoComprado = eventoParametro.comprarIngresso(tipoIngresso);
            if (ingressoComprado) {
                JOptionPane.showMessageDialog(null, "Ingresso comprado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Ingresso do tipo '" + tipoIngresso + "' indisponível.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Crie um evento antes de comprar ingressos.");
        }*/
    }

    private void emitirIngressoEletronico() {
    	/*
        if (eventoParametro != null) {
            String nomeCliente = JOptionPane.showInputDialog("Nome do Cliente:");
            String emailCliente = JOptionPane.showInputDialog("Email do Cliente:");
            Usuario cliente = new Usuario(nomeCliente, emailCliente);
            String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");
            eventoParametro.emitirIngressoEletronico(cliente, tipoIngresso);
        } else {
            JOptionPane.showMessageDialog(null, "Crie um evento antes de emitir ingressos eletrônicos.");
        } */
    }
}
