#input user,pass,asignatura_code(mid),carpeta
setvar(:name=>'username', :value=>'%%0%%')
setvar(:name=>'passwd', :value=>'%%1%%')
post(:URL=>'http://faitic.uvigo.es/index.php', :queryString=>'username=%%username%%&passwd=%%passwd%%&option=login')
append('http://faitic.uvigo.es/index.php?option=com_faitic_acceso_cursos&Itemid=67&lang=gl')
wget(:BRANCH_DUPLICATED, :SCATTERED){
 xpath('//table[@class="cursos"]//a')
 #xpath('//table[@class="cursos"]//@href') 
 match('&mid=(.*?)&')
}
