# secret-santa

Secret Santa generator with core.logic

## Usage

    (run 1 [q]
      (santa-pairso q '(chris
                        christine
                        russell
                        marie
                        sumeet
                        harish
                        rob))
      (not-pairso q 'chris 'christine)
      (not-pairso q 'chris 'russell)
      (not-pairso q 'christine 'russell)
      (not-pairso q 'russell 'marie))

## License

Copyright © 2012 Christopher Shea

Distributed under the MIT License.
