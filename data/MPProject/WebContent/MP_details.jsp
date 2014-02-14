<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mongodb.DBObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Open Parliament India @ OpenHackthon 2010</title>
  <meta name="robots" content="index, follow" />
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <meta name="author" content="RapidxHTML" />
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <!--[if lte IE 7]><link href="css/iehacks.css" rel="stylesheet" type="text/css" /><![endif]-->
  <script type="text/javascript" src="js/jquery.js"></script>
  <!--[if IE 6]>
  <script type="text/javascript" src="js/ie6pngfix.js"></script>
  <script type="text/javascript">
    DD_belatedPNG.fix('img, ul, ol, li, div, p, a, h1, h2, h3, h4, h5, h6');
  </script>
  <![endif]-->
  
   <link rel="stylesheet" href="style/style.css" type="text/css"/>

</head>

<body id="page">

<!-- wrapper -->
<div class="rapidxwpr floatholder">

  <!-- main body -->
  <div id="middle">
  
    <!-- left column -->
    <div id="left" class="clearingfix">
    
      <!-- logo -->
      <a href="index.html">
		<img id="logo" src="images/logo.png" alt="Home" title="Home" height="160" width="150" /></a>
      <!-- / logo -->
      
      <!-- menu -->
      <div class="menu">
        <ul>
          <li><a href="index.jsp" class="home">Home</a></li>
          <li><a href="searchbystate.jsp" class="commitment">Search By State</a></li>
          <li><a href="searchbyparty.jsp" class="organic">Search By Party</a></li>
          <li><a href="searchbyname.jsp" class="delivery">Search By Name</a></li>
          <li><a href="searchbyconstituency.jsp" class="delivery1">Search By Constituency</a></li>
          <li><a href="searchbyage.jsp" class="contact">Search By Age</a></li>
        </ul>
      </div>
      <!-- / menu -->
    
    </div>
    <!-- / left column -->
    
    <!-- content column -->
    <div id="main">
      <div id="main_container" class="clearingfix">
        <div id="mainmiddle" class="floatbox withright">
        
         <div id="wrapper">
         <div style="padding-left: 100px">
         <h3><%=session.getAttribute("mpname") %></h3>
         <br></br>
         <img src="<%=session.getAttribute("imageUrl") %>" align="middle" height="130" width="130" alt="<%=session.getAttribute("mpname") %>" />
         </div>
         <% ArrayList mps = (ArrayList)request.getAttribute("mps");
           if(mps!=null)
           {
           	  Iterator mpsIterator = mps.iterator();
           	  while(mpsIterator.hasNext())
           	  {
           		DBObject userdetails = (DBObject)mpsIterator.next();
           		Set fields = userdetails.keySet();
           		Iterator it = fields.iterator();%>
           		&nbsp;<ul id="index_cards">
           		<li id="card-1">
           		<%while(it.hasNext())
           		{
           			String fieldName = (String) it.next();
           			
           			if(fieldName.equalsIgnoreCase("_id")||fieldName.equalsIgnoreCase("image")||fieldName.equalsIgnoreCase("mp_id"))
           				continue;
           			String name = (String)userdetails.get(fieldName);
           %>
           
			
                	
                    	
                        
                        <p><strong><b><%=fieldName %></b></strong>: <%=name%><strong><br />
                        </p>    
                   
                
			<%}%>
			   </li>
			   </ul>
			<%
			}
           	  } %>	
            	
            </div>
   
        
        </div>
      </div>
    </div>
    <!-- / content column -->
  
  </div>
  <!-- / main body -->
  
  <!-- footer -->
  <div id="footer">
      © openparliamentindia | <a href="http://studio7designs.com/network">Designed by STUDIO7DESIGNS</a>
  </div>
  <!-- / footer -->

</div>
<!-- / wrapper -->

</body>
</html>