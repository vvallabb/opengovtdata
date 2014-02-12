<?php

class Pages extends CI_Controller{
	public function view ($page = 'home'){


	$data['title'] = ucfirst($page); // Capitalize the first letter

	$this->load->view('pages/'.$page, $data);
	$this->load->view('templates/examplejson', $data);
	}
}
