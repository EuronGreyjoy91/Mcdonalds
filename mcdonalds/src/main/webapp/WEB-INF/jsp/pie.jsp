<ul class="pagination center-align">

<!-- 	CALCULO DE ANTERIOR Y SIGUIENTE -->
	<c:set var = "anterior" value = "${pagina - 1}"/>
	<c:set var = "siguiente" value = "${pagina + 1}"/>
	
	<c:if test = "${anterior lt 0}">
		<c:set var = "anterior" value = "0"/>
	</c:if>
	
	<c:if test = "${siguiente gt paginas}">
		<c:set var = "siguiente" value = "${paginas}"/>
	</c:if>
	
<!-- 	CALCULO DE MINIMO Y MAXIMO -->
	<c:set var = "min" value = "${pagina - 2}"/>
	<c:set var = "max" value = "${pagina + 2}"/>
	
	<c:if test = "${min lt 0}">
		<c:set var = "min" value = "0"/>
	</c:if>
	
	<c:if test = "${max gt paginas}">
		<c:set var = "max" value = "${paginas}"/>
	</c:if>
	
<!-- 	CALCULO ESPECIAL PARA LAS PRIMERAS Y ULTIMAS PAGINAS -->
	<c:if test = "${pagina eq 0 and (max + 2 le paginas)}">
		<c:set var = "max" value = "${max + 2}"/>
	</c:if>
	
	<c:if test = "${pagina eq 1 and (max + 1 le paginas)}">
		<c:set var = "max" value = "${max + 1}"/>
	</c:if>
	
	<c:if test = "${pagina eq paginas and (min - 2 ge 0)}">
		<c:set var = "min" value = "${min - 2}"/>
	</c:if>
	
	<c:if test = "${pagina eq paginas - 1 and (min - 1 ge 0)}">
		<c:set var = "min" value = "${min - 1}"/>
	</c:if>
	
<!-- 	DIBUJO DEL PAGINADOR -->
	<li class="disabled"><a href="${urlListar}/${anterior}?<c:out value = "${queryString}"/>"><i class="material-icons">chevron_left</i></a></li>
	
	<c:forEach begin="${min}" end="${max}" varStatus="loop">
		<li class="waves-effect <c:if test = "${loop.index eq pagina}"> active </c:if>"><a href="${urlListar}/${loop.index}?<c:out value = "${queryString}"/>">${loop.index + 1}</a></li>
	</c:forEach>
	
	<li class="waves-effect"><a href="${urlListar}/${siguiente}?<c:out value = "${queryString}"/>"><i class="material-icons">chevron_right</i></a></li>
</ul>