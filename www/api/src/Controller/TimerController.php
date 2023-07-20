<?php

namespace App\Controller;

use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TimerController
{
    #[Route('/')]
    public function index(): Response
    {
        return new Response(
            '<h1>Pomodoro</h1>'
        );
    }
}