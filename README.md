# secret-santa

Secret Santa generator with core.logic

## Usage

Feed your list of participants and your list of pairs of people who
shouldn't be paired togther into `secret-santa`:

    (secret-santa ["chris" "christine" "russell" "marie" "harish" "sumeet"]
                  [["chris" "christine"]
                   ["chris" "russell"]
                   ["christine" "russell"]
                   ["russell" "marie"]
                   ["russell" "sumeet"]])

## License

Copyright © 2012 Christopher Shea

Distributed under the MIT License.
