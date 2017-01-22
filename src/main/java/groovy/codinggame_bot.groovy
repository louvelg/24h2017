@Grapes([
	@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7'),
	@Grab(group='org.apache.commons', module='commons-lang3', version='3.5')]
)
import static groovyx.net.http.ContentType.TEXT
import org.apache.commons.lang3.RandomStringUtils

def botId = "3"

def client = new groovyx.net.http.HTTPBuilder("http://localhost:8080/codingame/rest/v1/user/$botId/signin/latest")
client.setHeaders(Accept: 'application/json')
def json = client.get(contentType: TEXT)
def slurper = new groovy.json.JsonSlurper().parse(json)
//println slurper
def currentBot = slurper.currentBot
def gameId = slurper.gameId
//println "Bot lettre = " + slurper.currentBot


def status = ""
while(status != "over") {

	String randomString = RandomStringUtils.random(1, 'NSEW')
	String url = "http://localhost:8080/codingame/rest/v1/bot/${currentBot}/game/${gameId}/move/$randomString"
	client = new groovyx.net.http.HTTPBuilder(url)
	client.setHeaders(Accept: 'application/json')
	json = client.get(contentType: TEXT)
	slurper = new groovy.json.JsonSlurper().parse(json)
	//println slurper
	println "$slurper.lastTurn.indice / $slurper.maxTurn $url"
	status = slurper.status
	//println status
}

println "Finished"
