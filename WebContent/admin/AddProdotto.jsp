<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.control.HTMLUtil" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <title>Aggiungi prodotto</title>
</head>
<body>
    <%@ include file="../fragments/header.jsp" %>
    <%@ include file="../fragments/menu.jsp" %>

    <div id="main" class="clear">
        <h2>AGGIUNGI PRODOTTO</h2>
        <form action="../catalogo" method="post" id="myform">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="page" value="admin/GestioneCatalogo.jsp"><br><br>
            <div class="tableRow">
                <p>Nome:</p>
                <p><input type="text" name="nome" value="<%= request.getParameter("nome") != null ? escapeHTML(request.getParameter("nome")) : "" %>" required></p>
            </div>
            <!-- Rimuovi il campo Descrizione dettagliata, non è necessario in questo contesto -->
            <!-- Aggiungi il resto dei campi del form come descritto nell'originale -->
            <div class="tableRow">
                <p></p>
                <p><input type="submit" value="aggiungi"></p>
            </div>
        </form>
    </div>

    <%@ include file="../fragments/footer.jsp" %>

    <%
        // Funzione di escape per HTML
        public String escapeHTML(String input) {
            if (input == null) {
                return null;
            }
            return input.replaceAll("&", "&amp;")
                        .replaceAll("<", "&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\"", "&quot;");
        }
    %>
</body>
</html>