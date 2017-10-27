package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 1L;

	private DAO<Livro> dao = new DAO<Livro>(Livro.class);

	public LivroDataModel() {
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	@Override
	public List<Livro> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		System.out.println("Filtros:" + filters);
		
		
		String titulo = "";
		String coluna = "titulo";
		for(String key : filters.keySet()){
			titulo = (String) filters.get(key);
			coluna = key;
			break;
		}
		
		return dao.listaTodosPaginada(first, pageSize, coluna, titulo, sortField, sortOrder);
	}
}
