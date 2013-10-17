#input user,pass,asignatura_code(mid),carpeta
setvar(:name=>'mid', :value=>'%%0%%')
setvar(:name=>'idExer', :value=>'%%1%%')
setvar(:name=>'username', :value=>'%%2%%')
setvar(:name=>'pass', :value=>'%%3%%')

#AUTHENTICATION
post(:URL=>'http://faitic.uvigo.es/index.php', :queryString=>'username=%%username%%&passwd=%%pass%%&option=login')
append('http://faitic.uvigo.es/index2.php?option=com_faitic_acceso_curso&task=acceso&mid=%%mid%%&hidemainmenu=')
wget
branch(:BRANCH_DUPLICATED, :ORDERED){
    xpath('//form/@action')
    xpath('//form/input[@name="login"]/@value')
    xpath('//form/input[@name="password"]/@value')
    xpath('//form/input[@name="cid"]/@value')
    xpath('//form/input[@name="cidReq"]/@value')
    xpath('//form/input[@name="faitic_tipo_curso"]/@value')
}
post(:URL=>'%%0%%', :queryString=>'origen=tema&login=%%1%%&password=%%2%%&cid=%%3%%&cidReq=%%4%%&faitic_tipo_curso=%%5%%', :inputFilter=>'0-5')

append('http://cursos.faitic.uvigo.es/tema1314/claroline/work/workList.php?assigId=%%idExer%%')
wget

xpath('//blockquote')
