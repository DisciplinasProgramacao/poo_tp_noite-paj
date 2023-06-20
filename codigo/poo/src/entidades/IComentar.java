package entidades;
import excecoes.AvaliacaoInsuficienteException;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedMap;

public interface IComentar{
	
	

	private static boolean podeComentar(SortedMap<LocalDate,List<Midia>> listaMidiasAvaliadas) {
		LocalDate data = LocalDate.now().minusMonths(1);
		long count = listaMidiasAvaliadas.entrySet().stream().filter(m -> m.getKey().getMonth().compareTo(data.getMonth()) == 0).count();
		if(count < 5) {
			return false;
		}
		return true;
	}
	
	public static void comentar(String msg,Midia midia,SortedMap<LocalDate,List<Midia>> listaMidiasAvaliadas, String usuario) throws AvaliacaoInsuficienteException {
		if(podeComentar(listaMidiasAvaliadas)) {
			midia.comentar(msg,usuario);
		}else
			throw new AvaliacaoInsuficienteException("Não há 5 ou mais avaliações feitas no último mês!");
	}
	
}
