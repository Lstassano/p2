public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; 
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ProdottoDao dao = new ProdottoDao();
        
        ArrayList<ArrayList<ProdottoBean>> categorie = new ArrayList<>();
        String redirectedPage = request.getParameter("page");
        
        try {
            ArrayList<ProdottoBean> PS5 = dao.doRetrieveByPiattaforma("PlayStation 5");
            ArrayList<ProdottoBean> XboxSeries = dao.doRetrieveByPiattaforma("Xbox Series");
            ArrayList<ProdottoBean> Switch = dao.doRetrieveByPiattaforma("Nintendo Switch");
            ArrayList<ProdottoBean> PS4 = dao.doRetrieveByPiattaforma("PlayStation 4");
            ArrayList<ProdottoBean> Xone = dao.doRetrieveByPiattaforma("Xbox One");
            
            categorie.add(PS5);
            categorie.add(XboxSeries);
            categorie.add(Switch);
            categorie.add(PS4);
            categorie.add(Xone);

            request.getSession().setAttribute("categorie", categorie);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Validazione del parametro 'page'
        if (isInvalidPath(redirectedPage)) {
            // Se il percorso non è valido, reindirizza a una pagina di errore
            response.sendRedirect("/errorPage.jsp");
            return;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + redirectedPage);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // Metodo per validare il parametro 'page'
    private boolean isInvalidPath(String path) {
        if (path == null) {
            return true;
        }
        
        // Controllo se il percorso contiene riferimenti a file sensibili o directory non autorizzate
        String lowerCasePath = path.toLowerCase();
        if (lowerCasePath.contains("meta-inf/context.xml") || lowerCasePath.contains("web-inf/web.xml")) {
            return true;
        }

        // Impedisci riferimenti a percorsi relativi che escono dalla directory base
        if (path.contains("..")) {
            return true;
        }

        // Puoi aggiungere ulteriori controlli per altri file o directory sensibili qui

        return false;
    }
}