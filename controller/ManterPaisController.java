package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import service.ClienteService;
@@ -30,23 +32,75 @@ protected void doGet(HttpServletRequest request, HttpServletResponse response) t
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pNome = request.getParameter("nome");
        String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		String pId = request.getParameter("id");
		String pAcao = request.getParameter("acao");

		int id = -1;
		long populacao = -1;
		double area = -1;

		try {
			id = Integer.parseInt(pId);
			populacao = Long.parseLong(pPopulacao);
			area = Double.parseDouble(pArea);
		} catch (NumberFormatException e) {

		}

        Cliente cliente = new Cliente(id, pNome, populacao, area);
		ClienteService cs = new ClienteService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

        if (pAcao.equals("Criar")) {
			cs.create(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");

		} else if (pAcao.equals("Excluir")) {
			cs.delete(pais.getId());
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");

		} else if (pAcao.equals("Alterar")) {
			cs.update(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");

		} else if (pAcao.equals("Visualizar")) {
			pais = cs.read(pais);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");		
		} else if (pAcao.equals("Editar")) {
			pais = cs.read(pais);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");		
		}
		view.forward(request, response);
	}

	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == pais.getId()){
				return i;
			}
		}
		return -1;
	}
}