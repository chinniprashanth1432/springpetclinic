<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Pets</h2>

    <datatables:table id="pets" data="${pets.petList}" row="pet" theme="bootstrap2" cssClass="table table-striped"
                      pageable="false" info="false">
        <datatables:column title="Name">
            <c:out value="${pet.name} (ID=${pet.id})"></c:out>
        </datatables:column>
        <datatables:column title="Type">
            <c:out value="${pet.type.name}"/>
        </datatables:column>
        <datatables:column title="Owner">
            <c:out value="${pet.owner.firstName} ${pet.owner.lastName} (ID=${pet.owner.id})"/>
        </datatables:column>
        <datatables:column title="Visits">
            <c:forEach var="visit" items="${pet.visits}">
                <c:out value="${visit.date} Vet:${visit.vet.firstName} ${visit.vet.lastName} (ID=${visit.vet.id})"/>
                </br>
            </c:forEach>
            <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
        </datatables:column>
    </datatables:table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/pets.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/pets.json" htmlEscape="true" />">View as JSon</a>
            </td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
