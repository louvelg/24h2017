<?php

    define('ROOT_DIR', realpath(__DIR__).'/');
    require ROOT_DIR.'vendor/autoload.php';
    use GuzzleHttp\Client;

    const DEFAULT_HOST = "http://localhost:8080/codingame";

    const PATH_CREATE = "/rest/v1/game/{params}";
    const PATH_JOIN = "/rest/v1/user/{botId}/signin/{gameId}";
    const PATH_GAME = "/rest/v1/bot/{indice}/game/{gameId}/move/{move}";

    $clientJoin = new \GuzzleHttp\Client();
    $responseJoin = $clientJoin->get(DEFAULT_HOST . "/rest/v1/user/4/signin/latest");
    $jsonJoin = json_decode($responseJoin->getBody(), true);
    var_dump($jsonJoin);
    $currentBot = $jsonJoin['currentBot'];

    $co = null;

    $status = null;
    $over = null;
    while($status != "over") {
        $i = rand(0, 3);
        if( $i === 0 ) $co = "N";
        if( $i === 1 ) $co = "E";
        if( $i === 2 ) $co = "W";
        if( $i === 3 ) $co = "S";

        $clientMove = new \GuzzleHttp\Client();
        $url = DEFAULT_HOST . "/rest/v1/bot/".$jsonJoin['currentBot']."/game/".$jsonJoin['gameId']."/move/".$co;
        $responseMove = $clientMove->get($url);
        $jsonMove = json_decode($responseMove->getBody(), true);
        $status = $jsonMove['status'];

        var_dump("Tour : " . $jsonMove['lastTurn']['indice'] ." / ". $jsonMove['maxTurn'] . " - URL : " .  $url);
    }