<%-- 
    Document   : index
    Created on : 22/09/2018, 04:15:52 PM
    Author     : Federico
--%>
<%@ include file="cabecera.jsp" %>
    <script>
        var _alert = "<c:out value = '${errorMessage}'/>";
    </script>
    <script src = "${urlResources}/js/formLogin.js"></script>
        <h1 class = "center-align">McDonald's - Sistema de Gesti&oacute;n</h1><hr>
        <h3 class = "center-align">Ingreso al sistema</h3><br><br>
        <div class = "container">
            <div class="row">
                <form class="col s12" id = "login-form" name = "login-form" action = "${urlRoot}login" method = "POST">
                	<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
                    <div class="row">
                        <div class="input-field col s12 l6 offset-l3">
                            <i class="material-icons prefix">account_circle</i>
                            <input placeholder="Usuario" id="username" name = "username" type="text" class="validate" required>
                            <label for="user">Usuario </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 l6 offset-l3">
                            <i class="material-icons prefix">error</i>
                            <input id="password" name = "password" type="password" class="validate" required>
                            <label for="password">Contrase&ntilde;a</label>
                        </div>
                    </div>
                    <div class = "row center-align">
                        <button class="btn waves-effect waves-light btn-yellow" type="submit" name="action">Entrar
                            <i class="material-icons right">person</i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
