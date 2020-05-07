use std::{env, process};
use minigrep::{Config, run};

fn main() {
    let config = Config::new(env::args()).unwrap_or_else(|err| {
        eprintln!("Problem on read arguments: {}", err);
        process::exit(1);
    });
    println!("Searching for {} in file {}\n", config.query, config.filename);

    if let Err(e) = run(config) {
        eprintln!("Application error: {}", e);
        process::exit(1);
    };
}
