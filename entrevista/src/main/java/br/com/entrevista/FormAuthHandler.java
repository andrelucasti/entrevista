package br.com.entrevista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.entrevista.daos.DaoUsuario;
import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.TipoUsuario;
import br.com.entrevista.models.Usuario;


@Component
public class FormAuthHandler  implements AuthenticationSuccessHandler{
	
	@Autowired
	private DaoUsuario dao;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
			throws IOException, ServletException {
		
		String  username = req.getParameter("username");
		
		Usuario usuario = this.dao.findUsuarioByLogin(username);
		
		if (usuario != null) {
			
			
			
			for (TipoUsuario tpUsuario : usuario.getTipoUsuarios()) {
				if (tpUsuario.getTipoUsuario().equals(EnumTipoUsuario.ENTREVISTADO)) {
					req.getSession().setAttribute("usuarioEntrevistadoLogado", usuario);
					resp.sendRedirect( req.getContextPath() + "/homeEntrevistado.xhtml");
					
				}
				
				
				if (tpUsuario.getTipoUsuario().equals(EnumTipoUsuario.ENTREVISTADOR)) {
					resp.sendRedirect(req.getContextPath() +"/entrevistados.xhtml");
					
				}
				
			}
			
		} else {
			resp.sendRedirect(req.getContextPath() +"/falhaLogin.xhtml");
			
		}
				

		
		
	}

}
